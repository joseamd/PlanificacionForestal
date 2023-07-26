/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_fada;

/**
 *
 * @author neox
 */
public class MergeSort {
    
    public  void mergeSort( Rodal r[ ])
    {
        mergeSort( r, 0, r.length - 1 );
    }
    
    public  void mergeSort( Rodal r[ ] ,int left, int right ){
        
        Rodal tmpArray[] = new Rodal[ r.length ];
        if( left < right )
        {
            int center = ( left + right ) / 2;
            mergeSort( r, left, center );
            mergeSort( r, center + 1, right );
            merge( r, left, center + 1, right );
        }
    }
    

    public void merge( Rodal r[ ], int leftPos, int rightPos, int rightEnd){
        Rodal tmpArray[] = new Rodal[ r.length ];
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // Main loop
        while( leftPos <= leftEnd && rightPos <= rightEnd ){
            if( r[leftPos].getFechaFin() <  r[rightPos].getFechaFin())
            {
                tmpArray[ tmpPos++ ] = r[ leftPos++ ];
            }
            else{
                tmpArray[ tmpPos++ ] = r[ rightPos++ ];
            }
        }
        while( leftPos <= leftEnd ){    // Copy rest of first half
            tmpArray[ tmpPos++ ] = r[ leftPos++ ];
        }
        while( rightPos <= rightEnd ){  // Copy rest of right half
            tmpArray[ tmpPos++ ] = r[ rightPos++ ];
        }
        // Copy TmpArray back
        for( int i = 0; i < numElements; i++, rightEnd-- ){
            r[ rightEnd ] = tmpArray[ rightEnd ];
        }
    }
}
