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



============ CODICE ===========
{
	void x(int a){
		{
			print(a);
		}
	}
	x(4);
}
================================


; NEW BLOCK 
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; bring up the frame pointer
sw $fp 0($fp); save the old value
; BEGIN CALLING x
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
	 li $a0 4
push $a0 ; pushing 4
mv $fp $sp
addi $fp $fp 1
jal x; END CALLING x
halt
; BEGIN DEFINITION OF x:
b endendx
x:
sw $ra -1($cl)
; NEW BLOCK 
; BEGIN a EVAL 
 	 mv $al $fp 
	 lw $a0 -1($al)
; END a EVAL 
print $a0
; RETURN 
	 b endx
;END RETURN 
; END BLOCK
endx:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DEFINITION OF x
endendx:
; END BLOCK





========= CODICE ==========
{
	void x(int a){
		{
			print(a);
		}
	}
	x(4);
}
============================



; NEW BLOCK 
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; bring up the frame pointer
sw $fp 0($fp); save the old value
; BEGIN CALLING x
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
	 li $a0 4
push $a0 ; pushing 4
mv $fp $sp
addi $fp $fp 1
jal x; END CALLING x
halt
; BEGIN DEFINITION OF x:
b endendx
x:
sw $ra -1($cl)
; NEW BLOCK 
; NEW BLOCK 
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n =: 0)
; BEGIN a EVAL 
 	 mv $al $fp 
	 lw $al 0($al)
	 lw $a0 -1($al)
; END a EVAL 

print $a0


addi $sp $sp 0 ;pop var declarations
pop ;pop $al
pop ;pop consistency ra
lw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fp
pop ;pop old $fp
; END BLOCK
; RETURN 
	 b endx
;END RETURN 
; END BLOCK
endx:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DEFINITION OF x
endendx:
; END BLOCK



mettere un return quando una funzione è void




======== CODICE ========
{​​​
	int x(int a){​​​
		print(a);
		return a;
	}​​​
	print x(4);
}​​​
========================


; NEW BLOCK 
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; bring up the frame pointer
sw $fp 0($fp); save the old value
; BEGIN CALLING x
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
	 li $a0 4
push $a0 ; pushing 4

mv $fp $sp
addi $fp $fp 1
jal x; END CALLING x
print $a0
halt
; BEGIN DEFINITION OF x:
b endendx
x:
sw $ra -1($cl)
; NEW BLOCK 
; BEGIN a EVAL 
 	 mv $al $fp 
	 lw $a0 -1($al)
; END a EVAL 
print $a0
; RETURN 
; BEGIN a EVAL 
 	 mv $al $fp 
	 lw $a0 -1($al)
; END a EVAL 
	 b endx
;END RETURN 
; END BLOCK
endx:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DEFINITION OF x
endendx:
; END BLOCK
