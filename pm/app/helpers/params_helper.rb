module ParamsHelper

  # Метод формирования тега по типу
  def tag_from (type, name)
    case type
      when 'number'
        number_field_tag name, 0.0, :class => 'form-control'
      when 'text'
        text_field_tag name, '', :class => 'form-control'
      when 'textarea'
        text_area_tag name, '', :class => 'form-control'
      when 'checkbox'
        check_box_tag name, :checked => false, :class => 'form-control'
      else
      text_field_tag name, '', :class => 'form-control'
    end
  end

end
