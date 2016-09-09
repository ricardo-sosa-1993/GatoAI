package com.example.ricardo.gato;

import java.util.ArrayList;
import static java.util.Collections.max;
import static java.util.Collections.min;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Ricardo
 */
public class Position {
    public static final int DIM = 3;
    public static final int SIZE = DIM*DIM;
    public char turn;
    public char[] board;
    int dificultad; //3 dificil, 1 media, 0 facil

    // sale de aqui https://www.youtube.com/watch?v=9z2Z6xiauNA&feature=youtu.be minuto 28

    public Position(int dif) {
        dificultad = dif;
        turn = 'x';
        board = new char[SIZE];
        for(int i = 0; i < SIZE; i++){
            board[i]=' ';
        }
    }

    public Position(String string, char turn){
        this.board = string.toCharArray();
        this.turn = turn;
    }

    @Override
    public String toString() {
        return new String(board);
    }

    public Position move(int idx){
        board[idx]=turn;
        turn = (turn == 'x' ? 'o':'x');
        return this;
    }

    public Position unmove(int idx){
        board[idx] = ' ';
        turn = (turn == 'x' ? 'o':'x');
        return this;
    }

    public List<Integer> possibleMoves(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            if(board[i] == ' ')
                list.add(i);
        }
        return list;
    }

    public boolean isWinFor(char turn){
        boolean isWin = false;
        for(int i = 0; i < SIZE; i+=DIM){
            isWin = isWin || lineMatch(turn,i,i+DIM,1);
        }
        for(int i = 0; i < DIM; i++){
            isWin = isWin || lineMatch(turn, i, SIZE, DIM);
        }
        isWin = isWin || lineMatch(turn, 0, SIZE, DIM+1);
        isWin = isWin || lineMatch(turn, DIM-1, SIZE-1, DIM-1);
        return isWin;
    }

    private boolean lineMatch(char turn, int start, int end, int step) {
        for(int i = start; i < end; i+=step){
            if(board[i] != turn)
                return false;
        }
        return true;
    }

    public int blanks(){
        int total = 0;
        for(int i = 0; i < SIZE; i++){
            if(board[i] == ' ')
                total++;
        }
        return total;
    }

    public int minimax(int nivel){
        if(isWinFor('x')){

            return 100;
        }
        if(isWinFor('o')){

            return -100;
        }
        if(blanks()==0){
            return 0;
        }
        if(nivel == 0 && turn == 'x'){

            return evalua();
        }
        if(nivel == 0 && turn == 'o' ){

            return -evalua();
        }

        List<Integer> list = new ArrayList<>();
        for(Integer idx : possibleMoves()){
            list.add(move(idx).minimax(nivel -1));
            unmove(idx);
        }
        return turn == 'x' ? max(list) : min(list);

    }

    public int evalua(){
        int score=0;
        //tres
        if(board[0]==turn && board[1]==turn && board[2]==turn ){
            score = score + 100;
        }
        if(board[3]==turn && board[4]==turn && board[5]==turn ){
            score = score + 100;
        }
        if(board[6]==turn && board[7]==turn && board[8]==turn ){
            score = score + 100;
        }
        if(board[0]==turn && board[3]==turn && board[6]==turn  ){
            score = score + 100;
        }
        if(board[1]==turn && board[4]==turn && board[7]==turn ){
            score = score + 100;
        }
        if(board[2]==turn && board[5]==turn && board[8]==turn  ){
            score = score + 100;
        }
        if(board[0]==turn && board[4]==turn && board[8]==turn  ){
            score = score + 100;
        }
        if(board[2]==turn && board[4]==turn && board[6]==turn  ){
            score = score + 100;
        }
        //dos
        if(board[0]==turn && board[1]==turn && board[2]==' '  ||
                board[0]==' ' && board[1]==turn && board[2]==turn  ||
                board[0]==turn && board[1]==' ' && board[2]==turn  ){
            score = score + 10;
        }
        if(board[3]==turn && board[4]==turn && board[5]==' '  ||
                board[3]==' ' && board[4]==turn && board[5]==turn  ||
                board[3]==turn && board[4]==' ' && board[5]==turn  ){
            score = score + 10;
        }
        if(board[6]==turn && board[7]==turn && board[8]==' '  ||
                board[6]==' ' && board[7]==turn && board[8]==turn  ||
                board[6]==turn && board[7]==' ' && board[8]==turn  ){
            score = score + 10;
        }
        if(board[0]==turn && board[3]==turn && board[6]==' '  ||
                board[0]==' ' && board[3]==turn && board[6]==turn  ||
                board[0]==turn && board[3]==' ' && board[6]==turn  ){
            score = score + 10;
        }
        if(board[1]==turn && board[4]==turn && board[7]==' '  ||
                board[1]==' ' && board[4]==turn && board[7]==turn  ||
                board[1]==turn && board[4]==' ' && board[7]==turn  ){
            score = score + 10;
        }
        if(board[2]==turn && board[5]==turn && board[8]==' '  ||
                board[2]==' ' && board[5]==turn && board[8]==turn  ||
                board[2]==turn && board[5]==' ' && board[8]==turn  ){
            score = score + 10;
        }
        if(board[0]==turn && board[4]==turn && board[8]==' '  ||
                board[0]==' ' && board[4]==turn && board[8]==turn  ||
                board[0]==turn && board[4]==' ' && board[8]==turn  ){
            score = score + 10;
        }
        if(board[2]==turn && board[4]==turn && board[6]==' '  ||
                board[2]==' ' && board[4]==turn && board[6]==turn  ||
                board[2]==turn && board[4]==' ' && board[6]==turn  ){
            score = score + 10;
        }
        //uno
        if(board[0]==turn && board[1]==' ' && board[2]==' '  ||
                board[0]==' ' && board[1]==turn && board[2]==' '  ||
                board[0]==' ' && board[1]==' ' && board[2]==turn  ){
            score = score + 1;
        }
        if(board[3]==turn && board[4]==' ' && board[5]==' '  ||
                board[3]==' ' && board[4]==turn && board[5]==' '  ||
                board[3]==' ' && board[4]==' ' && board[5]==turn  ){
            score = score + 1;
        }
        if(board[6]==turn && board[7]==' ' && board[8]==' '  ||
                board[6]==' ' && board[7]==turn && board[8]==' '  ||
                board[6]==' ' && board[7]==' ' && board[8]==turn  ){
            score = score + 1;
        }
        if(board[0]==turn && board[3]==' ' && board[6]==' '  ||
                board[0]==' ' && board[3]==turn && board[6]==' '  ||
                board[0]==' ' && board[3]==' ' && board[6]==turn  ){
            score = score + 1;
        }
        if(board[1]==turn && board[4]==' ' && board[7]==' '  ||
                board[1]==' ' && board[4]==turn && board[7]==' '  ||
                board[1]==' ' && board[4]==' ' && board[7]==turn  ){
            score = score + 1;
        }
        if(board[2]==turn && board[5]==' ' && board[8]==' '  ||
                board[2]==' ' && board[5]==turn && board[8]==' '  ||
                board[2]==' ' && board[5]==' ' && board[8]==turn  ){
            score = score + 1;
        }
        if(board[0]==turn && board[4]==' ' && board[8]==' '  ||
                board[0]==' ' && board[4]==turn && board[8]==' '  ||
                board[0]==' ' && board[4]==' ' && board[8]==turn  ){
            score = score + 1;
        }
        if(board[2]==turn && board[4]==' ' && board[6]==' '  ||
                board[2]==' ' && board[4]==turn && board[6]==' '  ||
                board[2]==' ' && board[4]==' ' && board[6]==turn  ){
            score = score + 1;
        }
        return score;
    }

    public int bestMove(){
        Comparator<Integer> cmp = new Comparator<Integer>(){

            @Override
            public int compare(Integer first, Integer second) {     // nivel maximo 16/medio 8/ facil 5
                int a = move(first).minimax(dificultad);
                unmove(first);
                int b = move(second).minimax(dificultad);
                unmove(second);


                return a-b;
            }

        };
        List<Integer> list = possibleMoves();

        dificultad--;
        return turn == 'x' ? max(list,cmp) : min(list,cmp);

    }

    public boolean isGameEnd(){
        return isWinFor('x') || isWinFor('o') || blanks() == 0;
    }




}
