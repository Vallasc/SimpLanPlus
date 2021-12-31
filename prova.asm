;BEGIN BLOCK
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
;BEGIN DECVAR b
li $a0 4

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
lw $a0 -1($al)
;END ID
push $a0 ;pushing 
mv $fp $sp
addi $fp $fp 1
jal x;END DELETE
addi $sp $sp 1 ;pop var declarations
pop ;pop $alpop ;pop consistency ralw $cl 0($sp)
pop
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
;BEGIN ID 
mv $al $fp 
lw $a0 -1($al)
;END ID
print $a0
halt
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
