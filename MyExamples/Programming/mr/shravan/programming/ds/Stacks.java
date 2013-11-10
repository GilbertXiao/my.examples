package mr.shravan.programming.ds;

public class Stacks
{
        private int[] data;
        private int currPos;
        public Stacks(int size)
        {
          data = new int[size];
          currPos = 0;
        }
        private int pop()

        {
          if(currPos == 0)
          {
                throw new RuntimeException("Stack is empty");
          }
          return data[currPos--];
        }
        private void push(int d)
        {
           if(currPos == data.length)
           {
                throw new RuntimeException("Stack is full");
           }
           data[++currPos] = d;
        }
}
