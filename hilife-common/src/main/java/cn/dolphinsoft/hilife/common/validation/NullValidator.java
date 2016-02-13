package cn.dolphinsoft.hilife.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cn.dolphinsoft.hilife.common.validation.BaseValidator;

/**
 * Class Name: NullValidator
 * 
 * Description: TODO
 * 
 * @author hozhis
 *
 */
public class NullValidator extends BaseValidator implements ConstraintValidator<NullCheck, Object> {

    @Override
    public void initialize(NullCheck check) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return true;
    }

}
