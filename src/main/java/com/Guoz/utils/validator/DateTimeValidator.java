package com.Guoz.utils.validator;

import com.Guoz.utils.annotation.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @ClassName Guoz
 * @Data 14:59
 * @Version 1.0
 **/
public class DateTimeValidator implements ConstraintValidator<DateTime,String> {

    private DateTime dateTime;

    /**
     * 初始化，获取当前注释的所有属性
     * @param dateTime
     */
    @Override
    public void initialize(DateTime dateTime) {
        this.dateTime = dateTime;
    }


    /**
     * 约束验证的方法 value 参数，context 约束的上下文环境
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(@NotNull(message = "value不能为空") String value, ConstraintValidatorContext constraintValidatorContext) {
        // 如果 value 为空则不进行格式验证，为空验证可以使用 @NotBlank @NotNull @NotEmpty 等注解来进行控制，职责分离
        String format = dateTime.format();
        if (value.length() != format.length()) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            simpleDateFormat.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
