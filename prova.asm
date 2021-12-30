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
;BEGIN DECVAR b
li $a0 3

push $a0
;END DECVAR
;BEGIN ASSIGNMENT 
li $t1 -1
sw $t1 0($hp) 
push $a0
;BEGIN ID 
mv $al $fp 
addi $a0 $al -1
;END ID
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN ASSIGNMENT 
li $t1 -1
sw $t1 0($hp) 
push $a0
;BEGIN DER 
mv $al $fp
 addi $a0 $al -1
;END DER
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN ASSIGNMENT 
li $a0 4
push $a0
;BEGIN DER 
mv $al $fp
 addi $a0 $al -1
 lw $a0 0($a0)
;END DER
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN 
;BEGIN ID 
mv $al $fp 
lw $a0 -1($al)
;END ID
 lw $a0 0($a0)
;END DER
push $a0 ;push on the stack e1
;BEGIN ID 
mv $al $fp 
lw $a0 -2($al)
;END ID
lw $t1 0($sp) ;$t1 = e1, $a0 = e2
pop ;pop e1 from the stack
add $a0 $t1 $a0
;END 
print $a0
halt
; END BLOCK
