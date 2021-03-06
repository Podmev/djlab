package com.sdc.languages.kotlin.printers;

import com.sdc.languages.general.printers.OperationPrinter;

public class KotlinOperationPrinter extends OperationPrinter {
    protected static OperationPrinter ourInstance = new KotlinOperationPrinter();

    public static OperationPrinter getInstance(){
        return ourInstance;
    }

    @Override
    public String getDoubleCastView() {
        return ".toDouble()";
    }

    @Override
    public String getIntCastView() {
        return ".toInt()";
    }

    @Override
    public String getLongCastView() {
        return ".toLong()";
    }

    @Override
    public String getFloatCastView() {
        return ".toFloat()";
    }

    @Override
    public String getCharCastView() {
        return ".toChar()";
    }

    @Override
    public String getShortCastView() {
        return ".toShort()";
    }

    @Override
    public String getByteCastView() {
        return ".toByte()";
    }

    @Override
    public String getCheckCast(final String myParam) {
        return " as " + myParam;
    }

    public String getTypeWithBracketsView(String type) {
        return "Array<"+type+">";
    }

    public String getBooleanTypeView() {
        return "Boolean";
    }

    public String getIntTypeView() {
        return "Int";
    }

    public String getCharTypeView() {
        return "Char";
    }

    public String getShortTypeView() {
        return "Short";
    }

    public String getDoubleTypeView() {
        return "Double";
    }

    public String getFloatTypeView() {
        return "Float";
    }

    public String getByteTypeView() {
        return "Byte";
    }

    public String getLongTypeView() {
        return "Long";
    }

    public String getNotPrimitiveView(String className) {
        return className;
    }

    @Override
    public String getSpaceAfterType() {
        return "";
    }
}
