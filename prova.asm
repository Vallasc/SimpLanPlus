;BEGIN BLOCK
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
;BEGIN DECVAR x
addi $sp $sp -1
;END DECVAR
;BEGIN DECVAR boh
li $t1 -1
sw $t1 0($hp) 

push $a0
;END DECVAR
;BEGIN DECVAR y
li $t1 -1
sw $t1 0($hp) 

push $a0
;END DECVAR
;BEGIN ASSIGNMENT 
li $a0 0
push $a0
;BEGIN DER 
mv $al $fp
 addi $a0 $al -3
;END DER
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
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
;BEGIN ID 
mv $al $fp 
lw $a0 -3($al)
;END ID
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
li $a0 42
push $a0
;BEGIN DER 
mv $al $fp
 addi $a0 $al -2
;END DER
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
li $a0 129300
printchar $a0
;BEGIN CALL FUN [pippo]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
mv $fp $sp
addi $fp $fp 0
jal pippo;END DELETE
print $a0
li $a0 129300
printchar $a0
halt
;BEGIN DECFUN pippo
b endedpippo
pippo:
sw $ra -1($cl)
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
;BEGIN DECVAR a
li $t1 -1
sw $t1 0($hp) 

push $a0
;END DECVAR
;BEGIN DECVAR b
addi $sp $sp -1
;END DECVAR
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 2 ;frame pointer before decs (n = 2)
;BEGIN ASSIGNMENT 
;BEGIN ID 
mv $al $fp 
lw $a0 -1($al)
;END ID
push $a0
;BEGIN DER 
mv $al $fp
 addi $a0 $al -2
;END DER
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN ASSIGNMENT 
li $a0 475
push $a0
;BEGIN DER 
mv $al $fp
 addi $a0 $al -2
 lw $a0 0($a0)
;END DER
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN RETURN 
;BEGIN ID 
mv $al $fp 
lw $a0 -2($al)
;END ID
b endpippo
;END RETURN
addi $sp $sp 2 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fppop ;pop old $fp
; END BLOCK
endpippo:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DECFUN pippo
endedpippo:
; END BLOCK
