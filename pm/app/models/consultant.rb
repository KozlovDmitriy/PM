class Consultant < ActiveRecord::Base

  # Метод получения краткого имени консультанта.
  def short_name
    "#{family_name} #{first_name[0]}. #{second_name[0]}."
  end

  # Метод получения краткого имени консультанта.
  def full_name
    "#{family_name} #{first_name} #{second_name}"
  end

  has_many :input_data_items
  has_many :param_values

  # Метод получения отсортированных значений.
  def get_params_values
    ParamValue.where(:consultant_id => id).order(:analysis, :desc)
  end

end
