public class Train {

    private Node head;
    private Node headCargo;
    private Node tail;

    private int carNumber;

    private int engineNumber;
    private int cargoNumber;

    private int trainValue;

    public Train () {
        TrainCar engine = new TrainCar(TrainCar.ENGINE_TYPE, TrainCar.ENGINE_VALUE);
        head = new Node(engine, null, null);
        headCargo = new Node(new TrainCar(TrainCar.CARGO_DUMMY_TYPE, TrainCar.CARGO_DUMMY_VALUE), head, null);
        tail = new Node(new TrainCar(TrainCar.CARGO_DUMMY_TYPE, TrainCar.CARGO_DUMMY_VALUE), headCargo, null);

        head.setNext(headCargo);
        headCargo.setNext(tail);

        carNumber = 1;

        engineNumber = 1;
        cargoNumber = 0;
        trainValue = 0;
    }

    //Command PICKUP [num]
    //If train car is engine, add at the head or right after first engine
    //If train car is cargo, add at the end of the train
    public void pickup (Node node) {
        if (node.getTrainCarType().equals(TrainCar.ENGINE_TYPE)) {
            appendAfterNode(node, head);
        } else {
            appendAfterNode(node, tail.getPrev());
        };

        addTrainCarInfo(node);
   }

   private void appendAfterNode(Node node, Node afterThis) {
       node.setNext(afterThis.getNext());
       afterThis.setNext(node);

       node.setPrev(afterThis);
       node.getNext().setPrev(node);

   }

   private void addTrainCarInfo (Node node) {
        TrainCar car = node.getTrainCar();
        if (car.getCarType().equals(TrainCar.ENGINE_TYPE)) {
            engineNumber ++;
        } else {
            cargoNumber ++;
            trainValue += car.getCarValue();
        }
 
    }

    //Command PRINT
    public String trainInfo () {
        StringBuilder builder = new StringBuilder();
        //First line
        builder.append("Total number of engines: ");
        builder.append(engineNumber);
        builder.append(", Total number of cargo cars: ");
        builder.append(cargoNumber);
        builder.append(", Total value of cargo: $");
        builder.append(trainValue);
        builder.append("\n"); // end first line
        //Second line
        builder.append("The cars on the train are: ");
        // Engine list
        builder.append(TrainCar.ENGINE_TYPE);
        for (int i = 0; i < engineNumber-1; i ++) {
            builder.append(" - ");
            builder.append(TrainCar.ENGINE_TYPE);
        };
        // Cargo list
        Node iteratorCargo = headCargo.getNext();
        while (iteratorCargo != tail) {
            builder.append(" - ");
            builder.append(iteratorCargo.getTrainCarType());
            iteratorCargo = iteratorCargo.getNext();
        };

        return builder.toString();
    }

    //Command DROPLAST [num]
    //
    //return how many cars this droped
    public int removeLast (int carNumber) {

        if (carNumber >= cargoNumber) {
            //The case the number of cargos need removing is larger than the current number of cargos in the train
            int returnNumber = cargoNumber;
            removeAllCargo();
            return returnNumber;
        } else {
            Node iteratorCargo = tail.getPrev();
            for (int i = 0; iteratorCargo != null && i < carNumber; i ++) {
                removeTrainCarInfo(iteratorCargo);
                iteratorCargo = iteratorCargo.getPrev();
            };

            iteratorCargo.setNext(tail);
            tail.setPrev(iteratorCargo);

            return carNumber;
        }
    }

    //Command DROPFIRST [num]
    //Remove node containing cargo car 
    public int removeFirst (int carNumber) {
        
        if (carNumber >= cargoNumber) {
            //The case that the number of cargos need removing is larger than the current number of cargos in the train
            int returnNumber = cargoNumber;
            removeAllCargo();
            return returnNumber;
        } else {
            Node iteratorCargo = headCargo.getNext();
            for (int i = 0; iteratorCargo != null && i < carNumber; i ++) {
                removeTrainCarInfo (iteratorCargo);
                iteratorCargo = iteratorCargo.getNext();
            }; 

            headCargo.setNext(iteratorCargo);
            iteratorCargo.setPrev(headCargo);
            
            return carNumber;
        }
    }

    private void removeAllCargo () {
        trainValue = 0; 
        cargoNumber = 0; 
        headCargo.setNext(tail);
        tail.setPrev(headCargo);
    }

    //Command DROP [type] [num]
    public int removeType (String carType, int carNumber) {
        Node iteratorCargo = headCargo.getNext();
        int count = 0;
        for (int i = 0; iteratorCargo != null && i < carNumber && iteratorCargo.getTrainCarType() == carType; i ++ ,  iteratorCargo = iteratorCargo.getNext()) {
            removeTrainCar(iteratorCargo);
            count ++;
        }

        return count;
    }

    private void removeTrainCar (Node node) {
        Node prevNode = node.getPrev();
        Node nextNode = node.getNext();

        removeTrainCarInfo(node);
        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
    }

    private void removeTrainCarInfo(Node node) {
        boolean isCarEngine = node.getTrainCarType().equals(TrainCar.ENGINE_TYPE);

        if (isCarEngine) {
            engineNumber --;
        } else { 
            cargoNumber --;
            trainValue -= node.getTrainCarValue();
        }
        carNumber --;
    }

}
