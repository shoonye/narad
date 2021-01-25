package shoonye.event.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import shoonye.event.EventSender;
import shoonye.event.spec.Event;
import shoonye.event.spec.EventData;

@Service("eventSender")
public class RabbitMQEventSender implements EventSender {

    @Autowired private RabbitTemplate eventAmqpTemplate;

    @Value("${notification.enabled}") private boolean eventsEnabled;

    @Override
	public <T extends EventData> void sendEvent(final Event<T> event) {
		if(eventsEnabled){
		    if(TransactionSynchronizationManager.isActualTransactionActive()){
		        TransactionSynchronizationAdapter tsa = new TransactionSynchronizationAdapter(){
		            @Override
		            public void afterCommit() {
		                eventAmqpTemplate.convertAndSend(event);
		            }
		        };
                TransactionSynchronizationManager.registerSynchronization(tsa); 
		    }else{
		        eventAmqpTemplate.convertAndSend(event);
		    }			
		}		
	}

	@Override
	public <T extends EventData> void sendEvent(final String routingKey, final Event<T> event) {
		if(eventsEnabled){
		    if(TransactionSynchronizationManager.isActualTransactionActive()){
		        TransactionSynchronizationAdapter tsa = new TransactionSynchronizationAdapter(){
		            @Override
		            public void afterCommit() {
		                eventAmqpTemplate.convertAndSend(routingKey, event);
		            }
		        };
                TransactionSynchronizationManager.registerSynchronization(tsa); 
		    }else{
		        eventAmqpTemplate.convertAndSend(routingKey,event);
		    }			
		}		

		
	}
}
