 
public class ObserverPatternTest {
 
    public static void main(String[] args) {
        //create subject
        Subject mysubject = new Subject();
         
        //create observers
        IObserver obj1 = new Observer("Obj1");
        IObserver obj2 = new Observer("Obj2");
        IObserver obj3 = new Observer("Obj3");
         
        //register observers to the subject
        mysubject.register(obj1);
        mysubject.register(obj2);
        mysubject.register(obj3);
         
        //attach observer to subject
        obj1.setSubject(mysubject);
        obj2.setSubject(mysubject);
        obj3.setSubject(mysubject);
         
        //check if any update is available
        obj1.update();
         
        //now send message to subject
        mysubject.postMessage("New Message");
    }
 
}