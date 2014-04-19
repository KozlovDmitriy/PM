class Pm.Routers.ParamValuesRouter extends Backbone.Router
  initialize: (options) ->
    @paramValues = new Pm.Collections.ParamValuesCollection()
    @paramValues.reset options.paramValues

  routes:
    "new"      : "newParamValues"
    "index"    : "index"
    ":id/edit" : "edit"
    ":id"      : "show"
    ".*"        : "index"

  newParamValues: ->
    @view = new Pm.Views.ParamValues.NewView(collection: @param_values)
    $("#param_values").html(@view.render().el)

  index: ->
    @view = new Pm.Views.ParamValues.IndexView(param_values: @param_values)
    $("#param_values").html(@view.render().el)

  show: (id) ->
    param_values = @param_values.get(id)

    @view = new Pm.Views.ParamValues.ShowView(model: param_values)
    $("#param_values").html(@view.render().el)

  edit: (id) ->
    param_values = @param_values.get(id)

    @view = new Pm.Views.ParamValues.EditView(model: param_values)
    $("#param_values").html(@view.render().el)
