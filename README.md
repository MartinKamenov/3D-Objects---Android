# 3D Game Engine
Using oop principles one can create personal 3D Universe.

At this moment users can use engine to draw rotate and move different simple and complex objects.

## At this point engine supports drawing of following objects:

1. Cube
2. Paralleliped
3. Pyramid
4. Plane
5. Sphere
6. PartsObject
7. ComplexObject (Multiple objects rotating over one axis(its x, y and z))
8. Cylinder(in progress)

### Basic concepts
- Every object extends Object3D
- Every object has DeepPoints which is an array of points in space
- Every object can be rotated depending their rotation param
- Every object can be moved either by changing its x, y and z or using the MovingService

### DeepPoint:
- float x, float y, float z

### What params do we need for each element
#### Cube parameters:
- float x, float y, float z, float edgeLength, Paint edgePaint, Paint wallPaint, float rotation
#### Parallelepiped parameters:
- float x, float y, float z, float aLength, float bLength, float cLength, Paint edgePaint, Paint wallPaint, float rotation
#### Pyramid parameters:
- float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, float aLength, float bLength, float h(the depth of pyramid)
#### Plane parameters:
- float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, float aLength, float bLength
#### Sphere parameters:
- float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, float radius
#### PartsObject parameters(Object that is customized by the deepPoints and parts it has):
- float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, DeepPoint[] points, ArrayList<DeepPoint[]> parts
#### ComplexObject parameters:
- float x, float y, float z, Paint edgePaint, Paint wallPaint, float rotation, ArrayList<Object3D> objects

### Services
- DrawingService(knows how to draw objects)
- PaintService(knows how to generate paints for different objects)
- SortingService(used by drawing service and arranges objects so they are sorted by depth parameter)
- BreakingService(breaks object to smaller objects so every one is calculated more precisely where it is relative to others)
