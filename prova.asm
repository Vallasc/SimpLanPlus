;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
<<<<<<< HEAD
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
;BEGIN DECVAR a
addi $sp $sp -1
;END DECVAR
;BEGIN ASSIGNMENT 
li $t1 -1
sw $t1 0($hp) 
=======
;BEGIN DECVAR b
li $a0 4

>>>>>>> 83904902951005544cc91bfed1da75ccd3efe1fc
push $a0
;END DECVAR
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 1 ;frame pointer before decs (n = 1)
;BEGIN CALL FUN [x]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
;BEGIN ID 
mv $al $fp 
addi $a0 $al -1
;END ID
push $a0 ;pushing 
mv $fp $sp
addi $fp $fp 1
jal x;END DELETE
addi $sp $sp 1 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
<<<<<<< HEAD
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN ASSIGNMENT 
li $a0 4
push $a0
;BEGIN DER 
mv $al $fp
 addi $a0 $al -1
;END DER
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
;BEGIN CALL FUN [x]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
=======
lw $fp 0($sp) ;restore old $fppop ;pop old $fp
;BEGIN DECFUN x
b endx
x:
sw $ra -1($cl)
;BEGIN BLOCK
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
>>>>>>> 83904902951005544cc91bfed1da75ccd3efe1fc
;BEGIN ID 
mv $al $fp 
lw $a0 -1($al)
;END ID
push $a0 ;pushing 
li $a0 5
push $a0 ;pushing 
mv $fp $sp
addi $fp $fp 2
jal x;END DELETE
halt
;BEGIN DECFUN x
b endedx
x:
sw $ra -1($cl)
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN 
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $a0 -1($al)
;END ID
;END DER
push $a0 ;push on the stack e1
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $a0 -2($al)
;END ID
lw $t1 0($sp) ;$t1 = e1, $a0 = e2
pop ;pop e1 from the stack
add $a0 $t1 $a0
;END 
print $a0
;BEGIN RETURN 
b endx
;END RETURN
addi $sp $sp 0 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fppop ;pop old $fp
; END BLOCK
endx:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DECFUN x
endedx:
; END BLOCK
endx:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
endx:
;END DECFUN x
; END BLOCK
