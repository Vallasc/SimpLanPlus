;BEGIN BLOCK
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
;BEGIN DECVAR a
addi $sp $sp -1
;END DECVAR
;BEGIN ASSIGNMENT 
li $a0 5
push $a0
;BEGIN ID 
mv $al $fp 

 	 addi $a0 $al -1
      
lw $a0 -1($al)
;END ID
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN ID 
mv $al $fp 
lw $a0 -1($al)
;END ID
print $a0
halt
; END BLOCK
