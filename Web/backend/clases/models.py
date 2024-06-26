from django.db import models
from usuarios.models import Usuario

class Clase(models.Model):
    id_clase = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=45)
    precio = models.DecimalField(decimal_places=2, max_digits=8)
    descripcion = models.TextField(max_length=400)
    imagen = models.ImageField(upload_to='imagenes_clases/', null=True, blank=True)
    duracion = models.CharField(max_length=150)
    
    class Meta:
        db_table = "Clase"
        verbose_name = "Clase que da el GYM"
        verbose_name_plural = "Clases"
        
    def __unicode__(self):
        return self.nombre
    
    def __str__(self):
        return self.nombre
    
class UsuarioClase(models.Model):
    id_user_clase = models.AutoField(primary_key=True)
    id_clase = models.ForeignKey(Clase,on_delete=models.CASCADE)
    id_user = models.ForeignKey(Usuario, on_delete=models.CASCADE)
    
    class Meta:
        db_table = "UsuarioClase"
        verbose_name = "Clase del usuario"
        verbose_name_plural = "UsuariosClases"
        
    def __unicode__(self):
        return self.id_user_clase
    def __int__(self):
        return self.id_user_clase
    
class Pago(models.Model):
    id_pago = models.AutoField(primary_key=True)
    total = models.DecimalField(decimal_places=2, max_digits=8)
    fecha = models.DateTimeField(auto_now_add=True)
    id_user_clase= models.ForeignKey(UsuarioClase,on_delete=models.CASCADE)
    
    class Meta:
        db_table = "Pago"
        verbose_name = "Pago de clase"
        verbose_name_plural = "Pagos"
        
    def __unicode__(self):
        return self.id_pago
    def __int__(self):
        return self.id_pago