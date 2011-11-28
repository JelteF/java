import java.util.*;

public class Assignment7{
    public static void main(String[] args){
        Set a = new Set(20, 1, 1, 100);
        System.out.println("Set A:\n" + a + "\n");

        Set b = new Set(25, 3, 1, 100);
        System.out.println("Set B:\n" + b + "\n");

        System.out.println("A is a partial set of B: " + a.partialSetOf(b) + "\n");

        Set c = a.intersection(b);
        System.out.println("Intersection of A and B:\n" + c + "\n");

        Set d = a.union(b);
        System.out.println("Union of A and B:\n" + d + "\n");

        Set e = a.complement();
        System.out.println("Complement of A:\n" + e + "\n");
    }
}

class Set{
    private int size;
    private int start, end, maxSize;
    int[] elements;

    Set(){}
    Set(int size, int seed, int start, int end){
        elements = new int[size];
        this.size=size;
        this.start = start;
        this.end = end;
        maxSize = start+end-1;
        if(size>maxSize){

        }
        Random randomGenerator = new Random(seed);
        int[] randomArray= new int[maxSize];
        for(int i=0; i<maxSize; i++)
            randomArray[i] = i+start;
        for(int i=0; i<size; i++){
            int random = randomGenerator.nextInt(maxSize);
            int temp = randomArray[i];
            randomArray[i] = randomArray[random];
            randomArray[random] = temp;
        }
        System.arraycopy(randomArray, 0, elements, 0, size);
        sort();
    }
    Set(int[] elements){
        this.elements = elements;
        size = elements.length;
        sort();
        start = this.elements[0];
        end = this.elements[size-1];
        maxSize = start+end-1;


    }

    void sort(){
        boolean exchanged = true;
        int checkSize = size;
        while(exchanged){
            exchanged = false;
            for(int i=1; i<checkSize; i++){
                if(elements[i]<elements[i-1]){
                    int temp=elements[i];
                    elements[i]=elements[i-1];
                    elements[i-1]=temp;
                    exchanged = true;
                }
            }
            checkSize--;
        }
    }

    @Override public String toString(){
        String output= "{";
        for(int i=0; i<size-1; i++)
            output += elements[i] + ", ";
        if(size>1)
            output += elements[size-1];
        output += "}";
        return output;
    }

    boolean partialSetOf(Set s){
        if(this.size>s.size)
            return false;
        int lastHit = 0;
        for(int i=0; i<this.size; i++){
            for(int j=lastHit; j<s.size; j++){
                System.out.println(s.size-this.size+i);
                if(j>=s.size-this.size+i)
                    return false;
                if(this.elements[i]==s.elements[j]){
                    lastHit=j+1;
                    break;
                }
            }

        }
        return true;
    }

    Set intersection(Set s){
        Set small, big;
        if(this.size<s.size){
            small=this;
            big = s;
        }
        else{
            small = s;
            big = this;
        }
        int lastMiss = 0, hits=0;
        int[] temp = new int[small.size];
        for(int i=0; i<small.size; i++){
            for(int j=lastMiss; j<big.size; j++){
                if(big.elements[j]>small.elements[i]){
                    lastMiss=j;
                    break;
                }
                if(big.elements[j]==small.elements[i]){
                    temp[hits]=big.elements[j];
                    hits++;
                }
            }
        }
        int[] intersect = new int[hits];
        System.arraycopy(temp, 0, intersect, 0, hits);
        return new Set(intersect);
    }

    Set union(Set s){
        int[] temp = new int[this.size + s.size];
        Set small, big;
        if(this.size<s.size){
            small=this;
            big = s;
        }
        else{
            small = s;
            big = this;
        }
        System.arraycopy(small.elements, 0, temp, 0, small.size);
        int lastHit=0;
        int hits=0;
        for(int i=0; i<big.size; i++){
            for(int j=lastHit; j<small.size; j++){
                if(big.elements[i]==small.elements[j]){
                    lastHit=j+1;
                    break;
                }
                if(big.elements[i]<small.elements[j]){
                    temp[small.size+hits]=big.elements[i];
                    hits++;
                    lastHit=j;
                    break;
                }
            }
            if(big.elements[i]>small.elements[small.size-1]){
                temp[small.size+hits]=big.elements[i];
                hits++;
            }
        }
        System.out.println();
        int[] unionArr = new int[small.size + hits];
        System.arraycopy(temp, 0, unionArr, 0, unionArr.length);
        return new Set(unionArr);
    }

    Set complement(){
        int[] comp = new int[maxSize-size];
        int lastHit=0;
        int index=0;
        for(int i=0; i<size; i++){
            for(int j=lastHit; j<maxSize; j++){
                if(j+1 != elements[i]){
                    comp[index]=j+1;
                    index++;
                }
                else{
                    lastHit=j+1;
                    if(i+1<size)
                        break;
                }
            }
        }
        return new Set(comp);
    }

}