package team.weacsoft.classrepair.commons.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * easyExcel自定义转换类
 * @author GreenHatHG
 */
public class StateConverter implements Converter<Integer> {

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 这里读的时候会调用
     * @param cellData
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public Integer convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
       return cellData.getDataFormat();
    }

    /**
     * 这里是写的时候会调用 不用管
     * @param value
     * @param contentProperty
     * @param globalConfiguration
     * @return
     */
    @Override
    public CellData convertToExcelData(Integer value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        switch (value){
            case 1:
                return new CellData("待处理");
            case 2:
                return new CellData("处理中");
            case 3:
                return new CellData("已处理");
            case 4:
                return new CellData("已取消");
            default:
                return new CellData("异常订单");
        }
    }
}
