from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import ClaseViewSet, UsuarioClaseViewSet, PagoViewSet

router = DefaultRouter()
router.register(r'', ClaseViewSet)
router.register(r'usuariosclases', UsuarioClaseViewSet)
router.register(r'pagos', PagoViewSet)

urlpatterns = [
    path('', include(router.urls)),
]