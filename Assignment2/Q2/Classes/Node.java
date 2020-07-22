public class Node {

    private TrainCar trainCar;
    private Node prev;
    private Node next;

    public Node (TrainCar trainCar, Node prev, Node next) {
        this.trainCar = trainCar;
        this.prev = prev;
        this.next = next;
    }

    public TrainCar getTrainCar () {
        return this.trainCar;
    }

    public Node getNext () {
        return this.next;
    } 

    public Node getPrev () {
        return this.prev;
    }

    public void setNext (Node next) {
        this.next = next;
    } 

    public void setPrev (Node prev) {
        this.prev = prev;
    }

    public String getTrainCarType() {
        return trainCar.getCarType();
    }

    public int getTrainCarValue () {
        return trainCar.getCarValue();
    }

}
