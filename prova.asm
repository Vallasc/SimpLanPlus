;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
;BEGIN DECVAR b
addi $sp $sp 1 ;allocates space on the stack for arg [b]
addi $sp $sp -1
;END DECVAR
mv $fp $sp ;frame pointer above the new declarations
addi $fp $fp 1 ;frame pointer before decs (n = 1)
;BEGIN ITE 
;BEGIN ID 
mv $al $fp 
	 lw $a0 -1($al)
;END ID
li $t1 1
beq $a0 $t1 then1
	 b endif2
	then1:
; THAN
;BEGIN BLOCK
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp ;bring up the frame pointer
sw $fp 0($fp) ;save the old value
;BEGIN ASSIGNMENT 
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
	 lw $a0 -1($al)
;END ID
not $a0 $a0
push $a0
;BEGIN ID 
mv $al $fp 
lw $al 0($al)
	 lw $a0 -1($al)
;END ID
lw $t1 0($sp)
pop
sw $t1 0($a0)
;END ASSIGNMENT
halt
;END BLOCK
; END BLOCK
endif2 :
;END ITE
;END BLOCK
addi $sp $sp 1 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fppop ;pop old $fp
; END BLOCK
