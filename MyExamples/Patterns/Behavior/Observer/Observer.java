interface IObserver {
     
    //method to update the observer, used by subject
    public void update();
     
    //attach with subject to observe
    public void setSubject(ISubject sub);
}

public class Observer implements IObserver {
     
    private String name;
    private ISubject subject;
     
    public Observer(String nm){
        this.name=nm;
    }
    @Override
    public void update() {
        String msg = (String) subject.getUpdate(this);
        if(msg == null){
            System.out.println(name+":: No new message");
        }else
        System.out.println(name+":: Consuming message::"+msg);
    }
 
    @Override
    public void setSubject(ISubject sub) {
        this.subject=sub;
    }
 
}