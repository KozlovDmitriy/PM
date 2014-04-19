Pm.Views.ParamValues ||= {}

class Pm.Views.ParamValues.NewView extends Backbone.View
  template: JST["backbone/templates/param_values/new"]

  events:
    "submit #new-param_values": "save"

  constructor: (options) ->
    super(options)
    @model = new @collection.model()

    @model.bind("change:errors", () =>
      this.render()
    )

  save: (e) ->
    e.preventDefault()
    e.stopPropagation()

    @model.unset("errors")

    @collection.create(@model.toJSON(),
      success: (param_values) =>
        @model = param_values
        window.location.hash = "/#{@model.id}"

      error: (param_values, jqXHR) =>
        @model.set({errors: $.parseJSON(jqXHR.responseText)})
    )

  render: ->
    $(@el).html(@template(@model.toJSON() ))

    this.$("form").backboneLink(@model)

    return this
