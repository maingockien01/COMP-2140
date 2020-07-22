class Node {
    private String word;
    private Node next;

    public Node (String word, Node next) {
        this.word = word;
        this.next= next;
    }

    public Node getNext () {
        return this.next;
    }

    public void setNext (Node next) {
        this.next = next;
    }

    public String getWord () {
        return this.word;
    }

    public void setWord (String word) {
        this.word = word;
    }
}
