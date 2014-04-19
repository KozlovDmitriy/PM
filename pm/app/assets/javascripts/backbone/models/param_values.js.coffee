class Pm.Models.ParamValues extends Backbone.Model
  paramRoot: 'param_value'

  defaults:
    value: null
    param_id: null
    date_id: null
    consultant_id: null

class Pm.Collections.ParamValuesCollection extends Backbone.Collection
  model: Pm.Models.ParamValues
  url: '/param_values'
