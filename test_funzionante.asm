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

============ CODICE ===========
{
    ^^int x; 
    ^int y = new int; 
    y^ = 1; 
    print y^;
    x = new ^int; 
    x^ = y; 
    print x^^;
}
===============================

; NEW BLOCK 
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; bring up the frame pointer
sw $fp 0($fp); save the old value
; BEGIN ^^int x
	 addi $sp $sp -1
; END ^^int x
; BEGIN ^int y = new int
	 li $t1 -1
 	 sw $t1 0($hp) 
	 push $a0
; END ^int y = new int
; BEGIN y^ = 1
	 li $a0 1
	 push $a0
; BEGIN  DEREFERANTION NODE 
	 mv $al $fp
 	 addi $a0 $al -2

 	 lw $t1 0($sp)
	 lw $t1 0($sp)
	 pop
	 sw $t1 0($a0)
; END y^ = 1

; BEGIN y EVAL 
 	 mv $al $fp 
	 lw $a0 -2($al)
; END y EVAL 
 	 lw $a0 0($a0)
print $a0
; BEGIN x = new ^int
	 li $t1 -1
 	 sw $t1 0($hp) 
	 push $a0
; BEGIN  DEREFERANTION NODE 
	 mv $al $fp
 	 addi $a0 $al -1
	 lw $t1 0($sp)
	 pop
	 sw $t1 0($a0)
; END x = new ^int
; BEGIN x^ = y
; BEGIN y EVAL 
 	 mv $al $fp 
	 lw $a0 -2($al)
; END y EVAL 
	 push $a0
; BEGIN  DEREFERANTION NODE 
	 mv $al $fp
 	 addi $a0 $al -1
 	 lw $a0 0($a0)
	 lw $t1 0($sp)
	 pop
	 sw $t1 0($a0)
; END x^ = y
; BEGIN x EVAL 
 	 mv $al $fp 
	 lw $a0 -1($al)
; END x EVAL 
 	 lw $a0 0($a0)
 	 lw $a0 0($a0)
print $a0
halt
; END BLOCK




========== CODICE ===========
{
    void h(^int x, ^int y){ 
        if (y^==0) 
            delete x; 
        else { 
            x^ = x^ - 1; 
            h(y,x);
        }
    }
    ^int a = new int;
 ^int b = new int;
    a^ = 1;
    b^ = 4;
 h(b, a);
}
=============================

; NEW BLOCK 
push $sp
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; bring up the frame pointer
sw $fp 0($fp); save the old value
; BEGIN ^int a = new int
	 li $t1 -1
 	 sw $t1 0($hp) 
	 push $a0
; END ^int a = new int
; BEGIN ^int b = new int
	 li $t1 -1
 	 sw $t1 0($hp) 
	 push $a0
; END ^int b = new int
; BEGIN a^ = 1
	 li $a0 1
	 push $a0
; BEGIN  DEREFERANTION NODE 
	 mv $al $fp
 	 addi $a0 $al -1
 	 lw $a0 0($a0)
	 lw $t1 0($sp)
	 pop
	 sw $t1 0($a0)
; END a^ = 1
; BEGIN b^ = 4
	 li $a0 4
	 push $a0
; BEGIN  DEREFERANTION NODE 
	 mv $al $fp
 	 addi $a0 $al -2
 	 lw $a0 0($a0)
	 lw $t1 0($sp)
	 pop
	 sw $t1 0($a0)
; END b^ = 4



; BEGIN CALLING h
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
mv $al $fp
push $al
; BEGIN b EVAL 
 	 mv $al $fp 
	 lw $a0 -2($al)
; END b EVAL 
push $a0 ; pushing b
; BEGIN a EVAL 
 	 mv $al $fp 
	 lw $a0 -1($al)
; END a EVAL 
push $a0 ; pushing a
mv $fp $sp
addi $fp $fp 2
jal h; END CALLING h
halt



; BEGIN DEFINITION OF h:
b endendh
h:
sw $ra -1($cl)
; NEW BLOCK 

; BEGIN IF ; BEGIN y^ == 0
; BEGIN y EVAL 
 	 mv $al $fp 
	 lw $a0 -2($al)
; END y EVAL 
 	 lw $a0 0($a0)
	 push $a0 ; push on the stack e1
	 li $a0 0
	 lw $t1 0($sp) ;$t1 = e1, $a0 = e2
	 pop ;pop e1 from the stack
	 beq $t1 $a0 equalTrueBranch2
	 li $a0 0 ;e1 != e2
	 b endequalTrueBranch2
	 equalTrueBranch2:
	 li $a0 1 ;e1 == e2
	 endequalTrueBranch2:
	 ; END y^ == 0
 	 li $t1 1
	 beq $a0 $t1 then1
; BEGIN ELSE BRANCH 
; NEW BLOCK 
push $fp ;push old fp
push $cl
subi $sp $sp 1; ra 
mv $al $fp
push $al ;it's equal to the old $fp
mv $fp $sp; frame pointer above the new declarations
addi $fp $fp 0 ;frame pointer before decs (n =: 0)
; BEGIN x^ = x^ - 1
; BEGIN x^ - 1
; BEGIN x EVAL 
 	 mv $al $fp 
	 lw $al 0($al)
	 lw $a0 -1($al)
; END x EVAL 
 	 lw $a0 0($a0)
	 push $a0 ; push on the stack e1
	 li $a0 1
	 lw $t1 0($sp) ;$t1 = e1, $a0 = e2
	 pop ;pop e1 from the stack
	 sub $a0 $t1 $a0
	 ; END x^ - 1
	 push $a0
; BEGIN  DEREFERANTION NODE 
	 mv $al $fp
	 lw $al 0($al)
 	 addi $a0 $al -1
 	 lw $a0 0($a0)
	 lw $t1 0($sp)
	 pop
	 sw $t1 0($a0)
; END x^ = x^ - 1
; BEGIN CALLING h
push $fp
push $sp
mv $cl $sp
addi $t1 $cl 2
sw $t1 0($cl)
addi $sp $sp -1
lw $al 0($fp)
push $al
; BEGIN y EVAL 
 	 mv $al $fp 
	 lw $al 0($al)
	 lw $a0 -2($al)
; END y EVAL 
push $a0 ; pushing y
; BEGIN x EVAL 
 	 mv $al $fp 
	 lw $al 0($al)
	 lw $a0 -1($al)
; END x EVAL 
push $a0 ; pushing x
mv $fp $sp
addi $fp $fp 2
jal h; END CALLING h
addi $sp $sp 0 ;pop var declarations
pop ;pop $al
pop ;pop consistency ra
lw $cl 0($sp)
pop
lw $fp 0($sp) ;restore old $fp
pop ;pop old $fp
; END BLOCK
 ;END ELSE BRANCH 
	 b endifthen1
	then1:
; THAN BRANCH 
; BEGIN x EVAL 
 	 mv $al $fp 
	 lw $a0 -1($al)
; END x EVAL 
	 del $a0
 ;END THAN BRANCH 
; END IF 
endifthen1 :
; END BLOCK
endh:
lw $ra -1($cl)
lw $fp 1($cl)
lw $sp 0($cl) 
addi $cl $fp 2
jr $ra
;END DEFINITION OF h
endendh:
; END BLOCK
