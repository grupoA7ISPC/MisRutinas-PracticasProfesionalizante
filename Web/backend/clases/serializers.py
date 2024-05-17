from rest_framework import serializers
from .models import Clase, UsuarioClase, Pago

class ClaseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Clase
        fields = '__all__'

class UsuarioClaseSerializer(serializers.ModelSerializer):
    class Meta:
        model = UsuarioClase
        fields = '__all__'

class PagoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Pago
        fields = '__all__'