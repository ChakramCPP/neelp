import java.util.*;
/*
 * 1  S -> AA
 * 2  A -> aA | b
 * */
public class CLRParser {
    String table[][]= {
            {"s3","s4",null, "2", "1"},
            {null,null,"accepted", null, null},
            {"s3","s4",null, "5", null},
            {"s3","s4",null, "6", null},
            {"r3", "r3", "r3", null, null},
            {"r1", "r1", "r1", null, null},
            {"r2", "r2", "r2", null, null}};
    Stack<String> stack;
    String input;
    List<String> cols;
    List<String> rows;
    List<String> prods;
    List<String> vars;
    String colls[]={"a","b","$","A","S"};
    String rowws[]={"0","1","2","3","4","5","6"};
    String prodds[]={"AA","aA","b"};
    String varrs[]= {"S","A","A"};
    public CLRParser(String input) {
        stack=new Stack<>();
        stack.push("$");
        stack.push("0");
        this.input=input;
        cols=new ArrayList<>();
        cols.addAll(Arrays.asList(colls));
        rows=new ArrayList<>();
        rows.addAll(Arrays.asList(rowws));
        prods=new ArrayList<>();
        prods.addAll(Arrays.asList(prodds));
        vars=new ArrayList<>();
        vars.addAll(Arrays.asList(varrs));
    }
    void addToStack(String str,String inp){
        char ch=str.charAt(0);
        int num=Integer.parseInt(str.substring(1,str.length()));
        switch (ch){
            case 's':
                stack.push(inp);
                stack.push(num+"");
                input=input.substring(1,input.length());
                break;
            case 'r':
                int len=prods.get(num-1).length();
                for(int i=0;i<2*len;i++){
                    stack.pop();
                }
                int row=Integer.parseInt(stack.peek());
                int col=cols.indexOf(vars.get(num-1));
                stack.push(vars.get(num-1));
                stack.push(table[row][col]);
                break;
        }
    }
    void algorithm() {
        while(input.length()!=0){
            try{
                String str=input.charAt(0)+"";
                int c=cols.indexOf(str);
                int r=rows.indexOf(stack.peek());
                String ele=table[r][c];
                if(ele.equals("accepted")){
                    System.out.println("Input String is accepted");
                    break;
                }
                addToStack(ele,input.charAt(0)+"");
            }
            catch(Exception e){
                System.out.println("Input String is not accepted");
                break;
            }
        }
    }
    public static void main(String[] args) {
    	String input="aabb";
    	System.out.println("Input string is: "+input);
        CLRParser lParser=new CLRParser(input+"$");//*a=a
        lParser.algorithm();

    }
}
