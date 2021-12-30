;{
;	int a;
;   a = 5;
;	print(a);
;}


; NEW BLOCK 
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; bring up the frame pointer
sw $fp 0($fp); save the old value
; BEGIN int a
	 addi $sp $sp -1
; END int a
; BEGIN a = 5
	 li $a0 5
	 push $a0
; BEGIN  DEREFERANTION NODE 
	 mv $al $fp
 	 addi $a0 $al -1
	 lw $t1 0($sp)
	 pop
	 sw $t1 0($a0)
; END a = 5
; BEGIN a EVAL 
 	 mv $al $fp 
	 lw $a0 -1($al)
; END a EVAL 
print $a0
halt
; END BLOCK
