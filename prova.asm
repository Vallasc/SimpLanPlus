;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
;BEGIN DECVAR x
addi $sp $sp 1 ;allocates space on the stack for arg [x]
li $a0 1

push $a0
;END DECVAR
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 1 ;frame pointer before decs (n = 1)
;BEGIN CALL FUN [f]
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
li $a0 54
push $a0 ;pushing 
mv $fp $sp
addi $fp $fp 1
jal f;END DELETE
addi $sp $sp 1 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fppop ;pop old $fp
;BEGIN DECFUN f
b endf
f:
sw $ra -1($cl)
;BEGIN BLOCK
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
;BEGIN ITE 
;BEGIN 
;BEGIN ID 
mv $al $fp 
	 lw $a0 -1($al)
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
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $al 0($al)
	 lw $a0 -1($al)
;END ID
print $a0
addi $sp $sp 0 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fppop ;pop old $fp
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
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
lw $al 0($al)
	 lw $a0 -1($al)
;END ID
print $a0
addi $sp $sp 0 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fppop ;pop old $fp
; END BLOCK
endif2 :
;END ITE
;END BLOCK
halt
; END BLOCK
endf:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
endf:
;END DECFUN f
; END BLOCK
