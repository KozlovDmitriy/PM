Pm.Views.ParamValues ||= {}

class Pm.Views.ParamValues.ShowView extends Backbone.View
  template: JST["backbone/templates/param_values/show"]

  render: ->
    $(@el).html(@template(@model.toJSON() ))
    return this
