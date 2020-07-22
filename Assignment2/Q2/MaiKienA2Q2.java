/**
* Name of class or program (matches filename)
*
* COMP 2140 SECTION D01
* ASSIGNMENT    Assignment 2, question 1
* @author       Kien Mai - 7876083
* @version      June 12
*
* PURPOSE: 	Using doube-linked list to present a train.
*/

import java.util.*;
import java.io.*;

public class MaiKienA2Q2 {
    public static final String COMMAND_PICKUP = "PICKUP";
    public static final String COMMAND_PRINT = "PRINT";
    public static final String COMMAND_DROPLAST = "DROPLAST";
    public static final String COMMAND_DROPFIRST = "DROPFIRST";
    public static final String COMMAND_DROP = "DROP";

    public static final int INDEX_COMMAND = 0;

    public static final int INDEX_PICKUP_NUM = 1;
    public static final int INDEX_TRAIN_TYPE = 0;
    public static final int INDEX_TRAIN_VALUE = 1;

    public static final int INDEX_DROPLAST_NUM = 1;

    public static final int INDEX_DROPFIRST_NUM = 1;

    public static final int INDEX_DROP_TRAIN_CAR_TYPE = 1;
    public static final int INDEX_DROP_NUM = 2;

    public static void main(String[] args) {
        Train train = new Train();
        Scanner keyboardInput = new Scanner(System.in);

        System.out.println("Please provide the name of input file:");
        String fileName = keyboardInput.nextLine();

        try {
            File inputFile = new File(fileName);
            Scanner inputReader = new Scanner(inputFile);
            String line; 
            while (inputReader.hasNextLine() && (line = inputReader.nextLine()) != null) {
                System.out.println("Processing command: " + line);
                String[] info = line.split(" ");
                String command = info[INDEX_COMMAND];
                int carDropNum;
                switch (command) {
                    case COMMAND_PICKUP:
                        int engineNum = 0;
                        int cargoNum = 0;

                        int carNum = Integer.parseInt(info[INDEX_PICKUP_NUM]);

                        for (int i = 0; i < carNum; i ++) {
                            line = inputReader.nextLine();
                            if (line != null) {
                                String[] carInfo = line.split(" ");
                                
                                if (carInfo[INDEX_TRAIN_TYPE].equals(TrainCar.ENGINE_TYPE)) {
                                    TrainCar trainCar = new TrainCar(TrainCar.ENGINE_TYPE, TrainCar.ENGINE_VALUE);
                                    Node node = new Node(trainCar, null, null);
                                    train.pickup(node);
                                    engineNum ++;
                                } else {
                                    TrainCar trainCar = new TrainCar(carInfo[INDEX_TRAIN_TYPE], Integer.parseInt(carInfo[INDEX_TRAIN_VALUE]));
                                    Node node = new Node(trainCar, null, null);
                                    train.pickup(node);
                                    cargoNum ++;
                                }
                            };
                        };

                        System.out.println(reportPickup(engineNum, cargoNum));
                    break;

                    case COMMAND_PRINT:
                        String trainToString = train.trainInfo();
                        System.out.println(trainToString);
                    break;

                    case COMMAND_DROPLAST:
                        carDropNum = train.removeLast(Integer.parseInt(info[INDEX_DROPLAST_NUM]));
                        System.out.println(carDropNum + " cars dropped from train");
                    break;

                    case COMMAND_DROPFIRST:
                        carDropNum = train.removeFirst(Integer.parseInt(info[INDEX_DROPFIRST_NUM]));
                        System.out.println(carDropNum + " cars dropped from train");
                    break;

                    case COMMAND_DROP:
                        carDropNum = train.removeType(info[INDEX_DROP_TRAIN_CAR_TYPE], Integer.parseInt(info[INDEX_DROP_NUM]));
                        System.out.println(carDropNum + " cars dropped from train");
                    break;

                    default:
                    //Do nothing
                    break;

                }
            } //end file
            System.out.println("End of processing.");
            inputReader.close();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        };
    }

    private static String reportPickup(int engineNum, int cargoNum) {
        return engineNum + " engines and " + cargoNum + " cars added to train";
    }

} //End class MaiKienA2Q2

class Node {

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

} //End class Node

class Train {

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
        /*for (int i = 0; iteratorCargo != null && i < carNumber; i ++ ,  iteratorCargo = iteratorCargo.getNext()) {
            if (iteratorCargo.getTrainCarType().equals(carType)) {
                removeTrainCar(iteratorCargo);
                count ++;
            };
        } */

        for (int i = 0; i < cargoNumber && count < carNumber; i ++) {
            if(iteratorCargo.getTrainCarType().equals(carType)) {
                removeTrainCar(iteratorCargo);
                count ++;
            };
            iteratorCargo = iteratorCargo.getNext();
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

} //End class Train

class TrainCar {
    public static final String ENGINE_TYPE = "engine";
    public static final int ENGINE_VALUE = 0;

    public static final String CARGO_DUMMY_TYPE = "CargoDummy!";
    public static final int CARGO_DUMMY_VALUE = 0;

    private String carType;
    private int carValue;

    public TrainCar (String carType, int carValue) {
        this.carType = carType;
        this.carValue = carValue;
    }

    public String getCarType () {
        return this.carType;
    }

    public int getCarValue () {
        return this.carValue;
    }

} //End class TrainCar
