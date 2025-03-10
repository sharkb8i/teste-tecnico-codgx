using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System.Collections.Generic;
using System.Linq;

var builder = WebApplication.CreateBuilder(args);
builder.WebHost.UseUrls("http://0.0.0.0:5123");

builder.Services.AddCors(options =>
{
  options.AddPolicy("AllowAllOrigins", builder =>
    builder.AllowAnyOrigin()
           .AllowAnyMethod()
           .AllowAnyHeader());
});
builder.Services.AddControllers();
var app = builder.Build();

app.UseCors("AllowAllOrigins");
app.UseRouting();
app.UseEndpoints(endpoints => {
  endpoints.MapControllers();
});

app.Run();