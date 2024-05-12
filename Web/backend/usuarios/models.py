from django.db import models

class Rol(models.Model):
    id_rol  = models.AutoField(primary_key=True)
    nombre_rol = models.CharField(max_length=5) #Admin-Socio

    class Meta:
        db_table = "Rol"
        verbose_name = "Rol de usuario"
        verbose_name_plural = "Roles"
        
    def __unicode__(self):
        return self.nombre_rol
    
    def __str__(self):
        return self.nombre_rol
    
class Usuario(models.Model):
    id_user = models.AutoField(primary_key=True)
    id_rol = models.ForeignKey(Rol,on_delete=models.CASCADE,default=2) # 2=Socio
    nombre = models.CharField(max_length=15)
    apellido = models.CharField(max_length=15)
    fec_nac = models.DateField()
    email = models.EmailField(max_length=150)
    password = models.CharField(max_length=16)
    dni = models.IntegerField()
    tel = models.CharField(max_length=15)
    
    class Meta:
        db_table="Usuario"
        verbose_name="Usuario de MisRutinas"
        verbose_name_plural="Usuarios"

    # NOMBRE + APELLIDO
    def __unicode__(self):
        return self.nombre + " " + self.apellido
    
    # NOMBRE + APELLIDO
    def __str__(self):
        return self.nombre + " " + self.apellido