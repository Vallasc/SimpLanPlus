package com.unibo.ci.svm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.unibo.ci.svm.Instruction;
import com.unibo.ci.svm.lexer.SVMBaseVisitor;
import com.unibo.ci.svm.lexer.SVMParser;

public class SVMVisitorImpl extends SVMBaseVisitor<Void> {
    private final List<Instruction> code = new ArrayList<>();
    private HashMap<String, Integer> labelAdd = new HashMap<String, Integer>();
    private HashMap<Integer, String> labelRef = new HashMap<Integer, String>();

    public List<Instruction> getCode() {
        return code;
    }

    @Override
    public Void visitAssembly(SVMParser.AssemblyContext ctx) {
        visitChildren(ctx);
        for (Integer labelInt : labelRef.keySet()) {

            String labelString = labelRef.get(labelInt);
            Instruction instr = code.get(labelInt);
            if (instr.getInstruction().equals("beq") || instr.getInstruction().equals("bleq")) {
                code.set(labelInt, new Instruction(instr.getInstruction(), instr.getArg1(), 0, instr.getArg2(),
                        labelAdd.get(labelString).toString()));
            } else if (instr.getInstruction().equals("b") || instr.getInstruction().equals("jal")) {
                code.set(labelInt,
                        new Instruction(instr.getInstruction(), labelAdd.get(labelString).toString(), 0, null, null));
            }
        }
        return null;
    }

    @Override
    public Void visitPush(SVMParser.PushContext ctx) {
        code.add(new Instruction("push", ctx.REGISTER().getText(), 0, null, null));
        return null;
    }

    @Override
    public Void visitPop(SVMParser.PopContext ctx) {
        code.add(new Instruction("pop", null, 0, null, null));
        return null;
    }

    @Override
    public Void visitLw(SVMParser.LwContext ctx) {
        code.add(new Instruction("lw", ctx.out.getText(), Integer.parseInt(ctx.offset.getText()), ctx.in.getText(),
                null));
        return null;
    }

    @Override
    public Void visitSw(SVMParser.SwContext ctx) {
        code.add(new Instruction("sw", ctx.in.getText(), Integer.parseInt(ctx.offset.getText()), ctx.out.getText(),
                null));
        return null;
    }

    @Override
    public Void visitLi(SVMParser.LiContext ctx) {
        code.add(new Instruction("li", ctx.out.getText(), 0, ctx.in.getText(), null));
        return null;
    }

    @Override
    public Void visitMv(SVMParser.MvContext ctx) {
        code.add(new Instruction("mv", ctx.out.getText(), 0, ctx.in.getText(), null));
        return null;
    }

    @Override
    public Void visitAdd(SVMParser.AddContext ctx) {
        code.add(new Instruction("add", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitSub(SVMParser.SubContext ctx) {
        code.add(new Instruction("sub", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitMul(SVMParser.MulContext ctx) {
        code.add(new Instruction("mul", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitDiv(SVMParser.DivContext ctx) {
        code.add(new Instruction("div", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitAddi(SVMParser.AddiContext ctx) {
        code.add(new Instruction("addi", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitSubi(SVMParser.SubiContext ctx) {
        code.add(new Instruction("subi", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitMuli(SVMParser.MuliContext ctx) {
        code.add(new Instruction("muli", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitDivi(SVMParser.DiviContext ctx) {
        code.add(new Instruction("divi", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitAnd(SVMParser.AndContext ctx) {
        code.add(new Instruction("and", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitOr(SVMParser.OrContext ctx) {
        code.add(new Instruction("or", ctx.out.getText(), 0, ctx.in.getText(), ctx.in2.getText()));
        return null;
    }

    @Override
    public Void visitNot(SVMParser.NotContext ctx) {
        code.add(new Instruction("not", ctx.out.getText(), 0, ctx.in.getText(), null));
        return null;
    }

    @Override
    public Void visitBeq(SVMParser.BeqContext ctx) {
        labelRef.put(code.size(), ctx.LABEL().getText());
        code.add(new Instruction("beq", ctx.in.getText(), 0, ctx.in2.getText(), ctx.LABEL().getText()));
        return null;
    }

    public Void visitBleq(SVMParser.BleqContext ctx) {
        labelRef.put(code.size(), ctx.LABEL().getText());
        code.add(new Instruction("bleq", ctx.in.getText(), 0, ctx.in2.getText(), ctx.LABEL().getText()));
        return null;
    }

    @Override
    public Void visitB(SVMParser.BContext ctx) {
        labelRef.put(code.size(), ctx.LABEL().getText());
        code.add(new Instruction("b", ctx.LABEL().getText(), 0, null, null));
        return null;
    }

    @Override
    public Void visitLabel(SVMParser.LabelContext ctx) {
        labelAdd.put(ctx.LABEL().getText(), code.size());
        return null;
    }

    @Override
    public Void visitJal(SVMParser.JalContext ctx) {
        labelRef.put(code.size(), ctx.LABEL().getText());
        code.add(new Instruction("jal", ctx.LABEL().getText(), 0, null, null));
        return null;
    }

    @Override
    public Void visitJr(SVMParser.JrContext ctx) {
        code.add(new Instruction("jr", ctx.REGISTER().getText(), 0, null, null));
        return null;
    }

    @Override
    public Void visitDel(SVMParser.DelContext ctx) {
        code.add(new Instruction("del", ctx.REGISTER().getText(), 0, null, null));
        return null;
    }

    @Override
    public Void visitPrint(SVMParser.PrintContext ctx) {
        code.add(new Instruction("print", ctx.REGISTER().getText(), 0, null, null));
        return null;
    }

    @Override
    public Void visitHalt(SVMParser.HaltContext ctx) {
        code.add(new Instruction("halt", null, 0, null, null));
        return null;
    }

}