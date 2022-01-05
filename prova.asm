;BEGIN BLOCK
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
;BEGIN DECVAR alfa
li $t1 -1
sw $t1 0($hp) 

push $a0
;END DECVAR
;BEGIN DECVAR beta
li $t1 -1
sw $t1 0($hp) 

push $a0
;END DECVAR
;BEGIN DECVAR gamma
li $t1 -1
sw $t1 0($hp) 

push $a0
;END DECVAR
halt
;BEGIN DECFUN num
b endednum
num:
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
li $a0 0
;END RETURN
addi $sp $sp 0 ;pop var declarations
pop ;pop $al
pop ;pop consistency ra
lw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fp
pop ;pop old $fp
b endnum
; END BLOCK
endnum:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DECFUN num
endednum:
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
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN ITE 
;BEGIN 
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $a0 -2($al)
;END ID
push $a0 ; push on the stack e1
li $a0 0
lw $t1 0($sp) ;$t1 = e1, $a0 = e2
pop ;pop e1 from the stack
beq $t1 $a0 equalTrueBranch3
li $a0 0 ;e1 != e2
b endequalTrueBranch3
equalTrueBranch3:
li $a0 1 ;e1 != e2
endequalTrueBranch3:
;END 
li $t1 1
beq $a0 $t1 then1
;ELSE
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN CALL FUN [pippo]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
lw $al 0($fp)
lw $al 0($al)
lw $al 0($al)
push $al
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $al 0($al)
lw $a0 -1($al)
;END ID
push $a0 ;pushing 
;BEGIN 
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $al 0($al)
lw $a0 -2($al)
;END ID
push $a0 ;push on the stack e1
li $a0 1
lw $t1 0($sp) ;$t1 = e1, $a0 = e2
pop ;pop e1 from the stack
sub $a0 $t1 $a0
;END 
push $a0 ;pushing 
mv $fp $sp
addi $fp $fp 2
jal pippo;END DELETE
; END BLOCK
	 b endif2
	then1:
; THAN
;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n = 0)
;BEGIN DELETE 
mv $al $fp 
lw $al 0($al)
lw $al 0($al)
	 lw $a0 -1($al)
del $a0
;END DELETE
; END BLOCK
endif2 :
;END ITE
;BEGIN RETURN 
;BEGIN CALL FUN [num]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
lw $al 0($fp)
lw $al 0($al)
push $al
mv $fp $sp
addi $fp $fp 0
jal num;END DELETE
;END RETURN
addi $sp $sp 0 ;pop var declarations
pop ;pop $al
pop ;pop consistency ra
lw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fp
pop ;pop old $fp
b endpippo
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
