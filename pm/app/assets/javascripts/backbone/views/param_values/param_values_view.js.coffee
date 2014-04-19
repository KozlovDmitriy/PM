Pm.Views.ParamValues ||= {}

class Pm.Views.ParamValues.ParamValuesView extends Backbone.View
  template: JST["backbone/templates/param_values/param_values"]

  events:
    "click .destroy" : "destroy"

  tagName: "tr"

  destroy: () ->
    @model.destroy()
    this.remove()

    return false

  render: ->
    $(@el).html(@template(@model.toJSON() ))
    return this
