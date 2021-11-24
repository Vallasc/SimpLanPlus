grammar SVM;

assembly: (instruction)*;

instruction:
	'push' REGISTER													# push
	| 'pop'															# pop
	| 'lw' out = REGISTER offset = NUMBER '(' in = REGISTER ')'		# lw
	| 'sw' in = REGISTER offset = NUMBER '(' out = REGISTER ')'		# sw
	| 'li' out = REGISTER in = NUMBER								# li
	| 'mv' out = REGISTER in = REGISTER								# mv
	| 'add' out = REGISTER in = REGISTER in2 = REGISTER				# add
	| 'sub' out = REGISTER in = REGISTER in2 = REGISTER				# sub
	| 'mul' out = REGISTER in = REGISTER in2 = REGISTER				# mul
	| 'div' out = REGISTER in = REGISTER in2 = REGISTER				# div
	| 'addi' out = REGISTER in = REGISTER in2 = NUMBER				# addi
	| 'subi' out = REGISTER in = REGISTER in2 = NUMBER				# subi
	| 'muli' out = REGISTER in = REGISTER in2 = NUMBER				# muli
	| 'divi' out = REGISTER in = REGISTER in2 = NUMBER				# divi
	| 'and' out = REGISTER in = REGISTER in2 = REGISTER				# and
	| 'or' out = REGISTER in = REGISTER in2 = REGISTER				# or
	| 'not' out = REGISTER in = REGISTER							# not
	| 'beq' in = REGISTER in2 = REGISTER LABEL						# beq
	| 'bleq' in = REGISTER in2 = REGISTER LABEL						# bleq
	| 'b' LABEL														# b
	| LABEL ':'														# label
	| 'jal' LABEL													# jal
	| 'jr' REGISTER													# jr
	| 'del' REGISTER									            # del
	| 'print' REGISTER												# print
	| 'halt'														# halt
	;




// LABELS
LABEL: ('a' ..'z' | 'A' ..'Z') (
		'a' ..'z'
		| 'A' ..'Z'
		| '0' ..'9'
	)*;

// NUMBERS AND BOOLEANS
NUMBER: '0' | ('-')? (('1' ..'9') ('0' ..'9')*);

//REGISTERS
REGISTER:
	'$a0'  //results in the accumulator
	| '$t1' //tmp register
	| '$sp' //top of the stack
	| '$fp' //points to al relative to the active frame
	| '$al' //static chain for scopes
	| '$ra' //return address where the address of the next instruction is saved
	| '$hp' //pointer for the heap
	| '$cl' //control link, points at the previous frame (basically is the previous sp)
	;


// ESCAPE SEQUENCES
WS: ( '\t' | ' ' | '\r' | '\n')+ -> channel(HIDDEN);
LINECOMMENTS 	: ';' (~('\n'|'\r'))* -> skip;

ERR: . { errors.add("Invalid character: "+ getText()); } -> channel(HIDDEN);