from rest_framework import viewsets
from .models import Clase, UsuarioClase, Pago
from .serializers import ClaseSerializer, UsuarioClaseSerializer, PagoSerializer

class ClaseViewSet(viewsets.ModelViewSet):
    queryset = Clase.objects.all()
    serializer_class = ClaseSerializer

class UsuarioClaseViewSet(viewsets.ModelViewSet):
    queryset = UsuarioClase.objects.all()
    serializer_class = UsuarioClaseSerializer

class PagoViewSet(viewsets.ModelViewSet):
    queryset = Pago.objects.all()
    serializer_class = PagoSerializer