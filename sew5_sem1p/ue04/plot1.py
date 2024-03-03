import matplotlib.pyplot as plt
import math

PI = math.pi
CNT = 1024

werte = [ -PI + (PI/CNT * 2) * i for i in range(CNT)]
C = [math.cos(x) for x in werte]
S = [math.sin(x) for x in werte]

print(f"macht er die {len(werte)} und diese {len(C)}")

plt.figure(figsize=(10,6), dpi=80)
plt.plot(werte, C, color="green", linewidth=2.5, linestyle="-", label="Cosinus")
plt.plot(werte, S, color="red", linewidth=2.5, linestyle="-", label="Sinus")
plt.legend(loc='upper left', frameon=False)
plt.xlim(min(werte)*1.1, max(werte)*1.1)
plt.ylim(min(C)*1.1, max(C)*1.1)
plt.xticks([-PI, -PI/2, 0, PI/2, PI],
[r'$-\pi$', r'$-\pi/2$', r'$0$', r'$+\pi/2$', r'$+\pi$'])
plt.yticks([-1, 0, +1],
[r'$-1$', r'$0$', r'$+1$'])

ax = plt.gca()
ax.spines['right'].set_color('none')
ax.spines['top'].set_color('none')
ax.xaxis.set_ticks_position('bottom')
ax.spines['bottom'].set_position(('data',0))
ax.yaxis.set_ticks_position('left')
ax.spines['left'].set_position(('data',0))

t = 2*PI/3
plt.plot([t,t],[0,math.cos(t)], color ='green', linewidth=2.5, linestyle="--")
plt.scatter([t,],[math.cos(t),], 50, color ='green')
plt.annotate(r'$\sin(\frac{2\pi}{3})=\frac{\sqrt{3}}{2}$',
xy=(t, math.sin(t)), xycoords='data',
xytext=(+10, +30), textcoords='offset points', fontsize=16,
arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))
plt.plot([t,t],[0,math.sin(t)], color ='red', linewidth=2.5, linestyle="--")
plt.scatter([t,],[math.sin(t),], 50, color ='red')
plt.annotate(r'$\cos(\frac{2\pi}{3})=-\frac{1}{2}$',
xy=(t, math.cos(t)), xycoords='data',
xytext=(-90, -50), textcoords='offset points', fontsize=16,
arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))



plt.savefig("plot1_hirsch.png",dpi=72)
plt.show()


