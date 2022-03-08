import com.ib.client.EWrapper;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.*;
import com.ib.client.*;


public class MainLogic {

    EWrapperImpl eWrapperImpl;  
    private EReaderSignal readerSignal;
    EClientSocket client;
    OrdersContracts ordersAndContracts;
    int i = 1;

    public MainLogic (TextInterface _textInterface){
        eWrapperImpl = new EWrapperImpl(_textInterface);
        readerSignal = new EJavaSignal();
        client = new EClientSocket(eWrapperImpl, readerSignal);
        ordersAndContracts = new OrdersContracts();
        
        final EReader reader = new EReader(client, readerSignal);
        reader.start();
    }


    public void connect(String s, int p1, int p2){
        client.eConnect(s, p1, p2);
        i = 1;
        client.reqIds(i);
    }

    public void disconnect() {
        client.eDisconnect();
    }

    public int giveNextOrderId(){
        i++;
        return i; 
    }

    public void placeOrderFct(){
        client.placeOrder(giveNextOrderId(), ordersAndContracts.giveContract(), ordersAndContracts.giveOrder());
    }
}

