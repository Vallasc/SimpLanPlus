;BEGIN BLOCK
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
;BEGIN CALL FUN [x]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
lw $al 0($fp)
push $al
li $a0 4
push $a0 ;pushing 
mv $fp $sp
addi $fp $fp 1
jal x;END DELETE
print $a0
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
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN RETURN 
;BEGIN CALL FUN [double]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
lw $al 0($fp)
push $al
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $al 0($al)
lw $al 0($al)
lw $a0 -1($al)
;END ID
push $a0 ;pushing 
mv $fp $sp
addi $fp $fp 1
jal double;END DELETE
;END RETURN
addi $sp $sp 0 ;pop var declarations
pop ;pop $al
pop ;pop consistency ra
lw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fp
pop ;pop old $fp
b endx
;BEGIN DECFUN double
b endeddouble
double:
sw $ra -1($cl)
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN RETURN 
;BEGIN 
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $a0 -1($al)
;END ID
push $a0 ;push on the stack e1
li $a0 3
lw $t1 0($sp) ;$t1 = e1, $a0 = e2
pop ;pop e1 from the stack
mul $a0 $t1 $a0
;END 
;END RETURN
addi $sp $sp 0 ;pop var declarations
pop ;pop $al
pop ;pop consistency ra
lw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fp
pop ;pop old $fp
b enddouble
; END BLOCK
enddouble:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DECFUN double
endeddouble:
; END BLOCK
;BEGIN DECFUN double
b endeddouble
double:
sw $ra -1($cl)
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN RETURN 
;BEGIN 
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $a0 -1($al)
;END ID
push $a0 ;push on the stack e1
li $a0 2
lw $t1 0($sp) ;$t1 = e1, $a0 = e2
pop ;pop e1 from the stack
mul $a0 $t1 $a0
;END 
;END RETURN
addi $sp $sp 0 ;pop var declarations
pop ;pop $al
pop ;pop consistency ra
lw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fp
pop ;pop old $fp
b enddouble
; END BLOCK
enddouble:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DECFUN double
endeddouble:
; END BLOCK
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
