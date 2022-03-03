import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.*;
import com.ib.client.*;





public class Main extends JFrame implements EWrapper  {
    private EReaderSignal readerSignal;
    private EClientSocket clientSocket;
    JTextArea textArea1;
    JTextArea textArea2;
    public Main (String _name){
        super(_name);

        JPanel middlePanel = new JPanel();
        textArea1 = new JTextArea ("",14,50);
        textArea1.setEditable(false);
        textArea2 = new JTextArea ("",3,50);
        JScrollPane sp = new JScrollPane(textArea1);
        middlePanel.add(sp,BorderLayout.NORTH);
        middlePanel.add(textArea2,BorderLayout.SOUTH);

        JButton button = new JButton("Proceed");

        JPanel topPanel = new JPanel();
        JTextArea topPanelArea1 = new JTextArea("127.0.0.1",1,1);
        JTextArea topPanelArea2 = new JTextArea("7497",1,1);
        JTextArea topPanelArea3 = new JTextArea("2",1,1);
        JButton connectButton = new JButton("Connect");
        
        topPanel.setLayout(new FlowLayout());
        topPanel.add(topPanelArea1,BorderLayout.CENTER);
        topPanel.add(topPanelArea2,BorderLayout.CENTER);
        topPanel.add(topPanelArea3,BorderLayout.CENTER);
        topPanel.add(connectButton,BorderLayout.CENTER);
        
        readerSignal = new EJavaSignal();
        clientSocket = new EClientSocket( this, readerSignal);
        
        final EReader reader = new EReader(clientSocket, readerSignal);   
        
        reader.start();
        //An additional thread is created in this program design to empty the messaging queue
        new Thread(() -> {
            while (clientSocket.isConnected()) {
                readerSignal.waitForSignal();
                try {
                    reader.processMsgs();
                } catch (Exception e) {
                    textArea1.setText("Exception: "+e.getMessage());
                }
            }
        }).start();

        connectButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){

                connect(topPanelArea1.getText(),Integer.parseInt(topPanelArea2.getText()),Integer.parseInt(topPanelArea3.getText()));
            }
        });

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                clientSocket.placeOrder(1, giveContract(), giveOrder());
                
            }
        });







        this.add(topPanel,java.awt.BorderLayout.NORTH);
        this.add(middlePanel,java.awt.BorderLayout.CENTER);
        this.add(button,java.awt.BorderLayout.SOUTH);
        setSize(500,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void connect(String s, int p1, int p2){
        clientSocket.eConnect(s, p1, p2);
    }


    

    public Order giveOrder (){
        Order order1 = new Order();
        order1.action("BUY");
        order1.orderType("MKT");
        order1.totalQuantity(1);
        return order1;
    }
    public Contract giveContract (){
        Contract contract1 = new Contract ();
        contract1.symbol ("SPY");  
        contract1.secType ("STK");   
        contract1.currency ("USD");  
        contract1.exchange("SMART");
        contract1.primaryExch("ARCA");
        return contract1;
    }





    public static void main (String []args){
        Main main1 = new Main("Stock App");
    }



    public void tickPrice(int i, int i1, double v, int i2) {
        System.out.println("lol");
    }
    public void softDollarTiers(int arg0, SoftDollarTier[] arg1) {}





    @Override
    public void accountDownloadEnd(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void accountSummary(int arg0, String arg1, String arg2, String arg3, String arg4) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void accountSummaryEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void accountUpdateMulti(int arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void accountUpdateMultiEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void bondContractDetails(int arg0, ContractDetails arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void commissionReport(CommissionReport arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void completedOrder(Contract arg0, Order arg1, OrderState arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void completedOrdersEnd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void connectAck() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void connectionClosed() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void contractDetails(int arg0, ContractDetails arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void contractDetailsEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void currentTime(long arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deltaNeutralValidation(int arg0, DeltaNeutralContract arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void displayGroupList(int arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void displayGroupUpdated(int arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void error(Exception arg0) {
        textArea1.setText(textArea1.getText()+"\n"+"error");
    }

    @Override
    public void error(String arg0) {
        // TODO Auto-generated method stub
        textArea1.setText(textArea1.getText()+"\n"+arg0);
    }

    @Override
    public void error(int arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub
        textArea1.setText(textArea1.getText()+"\n"+arg0 + arg1 + arg2);
    }

    @Override
    public void execDetails(int arg0, Contract arg1, Execution arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execDetailsEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void familyCodes(FamilyCode[] arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void fundamentalData(int arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void headTimestamp(int arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void histogramData(int arg0, List<HistogramEntry> arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalData(int arg0, Bar arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalDataEnd(int arg0, String arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalDataUpdate(int arg0, Bar arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalNews(int arg0, String arg1, String arg2, String arg3, String arg4) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalNewsEnd(int arg0, boolean arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalTicks(int arg0, List<HistoricalTick> arg1, boolean arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalTicksBidAsk(int arg0, List<HistoricalTickBidAsk> arg1, boolean arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void historicalTicksLast(int arg0, List<HistoricalTickLast> arg1, boolean arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void managedAccounts(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void marketDataType(int arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void marketRule(int arg0, PriceIncrement[] arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mktDepthExchanges(DepthMktDataDescription[] arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void newsArticle(int arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void newsProviders(NewsProvider[] arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void nextValidId(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void openOrder(int arg0, Contract arg1, Order arg2, OrderState arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void openOrderEnd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void orderBound(long arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void orderStatus(int arg0, String arg1, double arg2, double arg3, double arg4, int arg5, int arg6,
            double arg7, int arg8, String arg9, double arg10) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pnl(int arg0, double arg1, double arg2, double arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pnlSingle(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void position(String arg0, Contract arg1, double arg2, double arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void positionEnd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void positionMulti(int arg0, String arg1, String arg2, Contract arg3, double arg4, double arg5) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void positionMultiEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void realtimeBar(int arg0, long arg1, double arg2, double arg3, double arg4, double arg5, long arg6,
            double arg7, int arg8) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void receiveFA(int arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rerouteMktDataReq(int arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rerouteMktDepthReq(int arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void scannerData(int arg0, int arg1, ContractDetails arg2, String arg3, String arg4, String arg5,
            String arg6) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void scannerDataEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void scannerParameters(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void securityDefinitionOptionalParameter(int arg0, String arg1, int arg2, String arg3, String arg4,
            Set<String> arg5, Set<Double> arg6) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void securityDefinitionOptionalParameterEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void smartComponents(int arg0, Map<Integer, Entry<String, Character>> arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void symbolSamples(int arg0, ContractDescription[] arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickByTickAllLast(int arg0, int arg1, long arg2, double arg3, int arg4, TickAttribLast arg5,
            String arg6, String arg7) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickByTickBidAsk(int arg0, long arg1, double arg2, double arg3, int arg4, int arg5,
            TickAttribBidAsk arg6) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickByTickMidPoint(int arg0, long arg1, double arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickEFP(int arg0, int arg1, double arg2, String arg3, double arg4, int arg5, String arg6, double arg7,
            double arg8) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickGeneric(int arg0, int arg1, double arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickNews(int arg0, long arg1, String arg2, String arg3, String arg4, String arg5) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickOptionComputation(int arg0, int arg1, double arg2, double arg3, double arg4, double arg5,
            double arg6, double arg7, double arg8, double arg9) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickPrice(int arg0, int arg1, double arg2, TickAttrib arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickReqParams(int arg0, double arg1, String arg2, int arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickSize(int arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickSnapshotEnd(int arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void tickString(int arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAccountTime(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateAccountValue(String arg0, String arg1, String arg2, String arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateMktDepth(int arg0, int arg1, int arg2, int arg3, double arg4, int arg5) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateMktDepthL2(int arg0, int arg1, String arg2, int arg3, int arg4, double arg5, int arg6,
            boolean arg7) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updateNewsBulletin(int arg0, int arg1, String arg2, String arg3) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void updatePortfolio(Contract arg0, double arg1, double arg2, double arg3, double arg4, double arg5,
            double arg6, String arg7) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void verifyAndAuthCompleted(boolean arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void verifyAndAuthMessageAPI(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void verifyCompleted(boolean arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void verifyMessageAPI(String arg0) {
        // TODO Auto-generated method stub
        
    }
    






}
