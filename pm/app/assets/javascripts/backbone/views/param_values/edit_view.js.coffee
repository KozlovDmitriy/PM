Pm.Views.ParamValues ||= {}

class Pm.Views.ParamValues.EditView extends Backbone.View
  template : JST["backbone/templates/param_values/edit"]

  events :
    "submit #edit-param_values" : "update"

  update : (e) ->
    e.preventDefault()
    e.stopPropagation()

    @model.save(null,
      success : (param_values) =>
        @model = param_values
        window.location.hash = "/#{@model.id}"
    )

  render : ->
    $(@el).html(@template(@model.toJSON() ))

    this.$("form").backboneLink(@model)

    return this
