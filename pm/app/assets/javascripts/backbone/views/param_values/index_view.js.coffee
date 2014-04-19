Pm.Views.ParamValues ||= {}

class Pm.Views.ParamValues.IndexView extends Backbone.View
  template: JST["backbone/templates/param_values/index"]

  initialize: () ->
    @options.paramValues.bind('reset', @addAll)

  addAll: () =>
    @options.paramValues.each(@addOne)

  addOne: (paramValues) =>
    view = new Pm.Views.ParamValues.ParamValuesView({model : paramValues})
    @$("tbody").append(view.render().el)

  render: =>
    $(@el).html(@template(paramValues: @options.paramValues.toJSON() ))
    @addAll()

    return this
