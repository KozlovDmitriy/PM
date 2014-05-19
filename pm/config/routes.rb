Pm::Application.routes.draw do

  resources :configures

  get 'department-plans.json' => 'pages#department_plans'
  get 'month' => 'pages#month'
  get 'chart.json' => 'pages#chart'
  get 'form_report/index'
  get 'new-analyse' => 'analysis_controller#new_index'
  get '/get-new-analyse.json' => 'analysis_controller#get_new_index'
  post 'form_report/get_report' => 'form_report#get_report'
  get 'form_report/get_report' => 'form_report#get_report'
  resources :analyses

  post '/save_params' => 'input_data#save_form_params'

  get 'analysis_controller/index'

  post '/analyse/problems.json' => 'analysis_controller#analyse_problems'
  post '/analyse/new_analyse_problems.json' => 'analysis_controller#new_analyse_problems'
  post '/analyse/solutions.json' => 'analysis_controller#analyse_solution'
  resources :input_data_items

  resources :input_data

  resources :param_values

  resources :params

  resources :solutions

  resources :problems

  resources :consultants

  get 'pages/index'

  root 'pages#index'

  get '/upload/excel.json' => 'input_data#parse_excel'

  post '/upload/excel.json' => 'input_data#parse_excel'

  # The priority is based upon order of creation: first created -> highest priority.
  # See how all your routes lay out with "rake routes".

  # You can have the root of your site routed with "root"
  # root 'welcome#index'

  # Example of regular route:
  #   get 'products/:id' => 'catalog#view'

  # Example of named route that can be invoked with purchase_url(id: product.id)
  #   get 'products/:id/purchase' => 'catalog#purchase', as: :purchase

  # Example resource route (maps HTTP verbs to controller actions automatically):
  #   resources :products

  # Example resource route with options:
  #   resources :products do
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
end
