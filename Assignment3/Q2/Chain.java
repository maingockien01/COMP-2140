class Chain {
    private Node top;
    private Node tail;

    public Chain () {
        top = null;
        tail = null;
    }

    public Chain (String newWord) {
        top = new Node(newWord, null);
        tail = top;
    }

    public void insert (String word) {
        if (top == null) {
            top = new Node(word, null);
            tail = top;
        };
        Node newNode = new Node(word, null);
        tail.setNext(newNode);
        tail = newNode;
        
    }

    public boolean search (String word) {
        if (top == null) {
            return false;
        };
        Node curr = top;
        while (curr != null) {
            String currWord = curr.getWord();
            if (currWord.compareTo(word) == 0) {
                return true;
            };
            curr = curr.getNext();
        };
        return false;
    }

    public Node getTop () {
        return this.top;
    }

}
