import random
class Pos:
    def __init__(self,x,y):
        self.x=x
        self.y=y
class Mohreh:
    def __init__(self,name,power,distance,x,y):
        self.name=name
        self.power=power
        self.distance=distance
        self.pos=Pos(x,y)
class Bazikon:
    def __init__(self,name_e,power_e,distance_e,pos_e_x,pos_e_y,name_h,power_h,distance_h,pos_h_x,pos_h_y,name_t,power_t,distance_t,pos_t_x,pos_t_y,number):
        self.m_e=Mohreh(name_e,power_e,distance_e,pos_e_x,pos_e_y)
        self.m_h=Mohreh(name_h,power_h,distance_h,pos_h_x,pos_h_y)
        self.m_t=Mohreh(name_t,power_t,distance_t,pos_t_x,pos_t_y)
        self.number=number
    def change_dir(self,mohreh,direction,distance):
        if(direction=="u"):
            m_pos_x=mohreh.pos.x-distance
            m_pos_y=mohreh.pos.y
        elif(direction=="d"):
            m_pos_x=mohreh.pos.x+distance
            m_pos_y=mohreh.pos.y
        elif(direction=="r"):
            m_pos_x=mohreh.pos.x
            m_pos_y=mohreh.pos.y+distance
        else:
            m_pos_x=mohreh.pos.x
            m_pos_y=mohreh.pos.y-distance
        return (m_pos_x,m_pos_y)
    def search(self,mohreh,direction,distance,z_bazi):
        tu_ch=self.change_dir(mohreh,direction,distance)
        if(z_bazi[tu_ch[0]][tu_ch[1]]==None):
            return True
        else:
            if(z_bazi[tu_ch[0]][tu_ch[1]].name.isupper() == mohreh.name.isupper() or z_bazi[tu_ch[0]][tu_ch[1]].name.islower() == mohreh.name.islower()):
                return False
            elif(z_bazi[tu_ch[0]][tu_ch[1]].name.isupper() != mohreh.name.isupper() and z_bazi[tu_ch[0]][tu_ch[1]].power<=mohreh.power):
                return True
            else:
                return False
    def move(self,mohreh,direction,distance,z_bazi):
        tu_ch=self.change_dir(mohreh,direction,distance)
        if(self.search(mohreh,direction,distance,z_bazi)==True):
            z_bazi[tu_ch[0]][tu_ch[1]]=mohreh
            z_bazi[mohreh.pos.x][mohreh.pos.y]=None
            mohreh.pos.x=tu_ch[0]
            mohreh.pos.y=tu_ch[1]
        else:
            print("harekat gheyr mojaz!!")
bazikon_1=Bazikon("e",2,2,0,0,"h",1,1,0,1,"t",3,1,0,2,1)
bazikon_2=Bazikon("E",2,2,2,0,"H",1,1,2,1,"T",3,1,2,2,2)
zamin_b=[[bazikon_1.m_e,bazikon_1.m_h,bazikon_1.m_t],[None,None,None],[bazikon_2.m_e,bazikon_2.m_h,bazikon_2.m_t]]
def print_z(zamin_b):
    for i in range(len(zamin_b)):
        for j in range(len(zamin_b[0])):
            if(zamin_b[i][j]==None):
                print("*\t",end="")
            else:
                print(zamin_b[i][j].name+"\t",end="")
        print()
def interface_b(bazikon):
    if(bazikon.number==1):
        while 1:
            mohreh=input("Player 1 choose mohre(e h t):")
            direction=input("Choose direction: (L U R D) : (Left , Right , Up , Bottom):")
            distance=int(input("Choose Distance:"))
            if((direction=="l" or direction=="r" or direction=="d" or direction=="u")):
                if(mohreh=="e" and distance<=bazikon.m_e.distance):
                    break
                elif(mohreh=="h" and distance<=bazikon.m_h.distance):
                    break
                elif(mohreh=="t" and distance<=bazikon.m_t.distance):
                    break
                else:
                    print("lotfan etelaat ra sahih vared konid!!!!")
                    pass
    else:
        while 1:
            mohreh=input("Player 2 choose mohre(E H T):")
            direction=input("Choose direction: (L U R D) : (Left , Right , Up , Bottom):")
            distance=int(input("Choose Distance:"))
            if((direction=="l" or direction=="r" or direction=="d" or direction=="u")):
                if(mohreh=="E" and distance<=bazikon.m_e.distance):
                    break
                elif(mohreh=="H" and distance<=bazikon.m_h.distance):
                    break
                elif(mohreh=="T" and distance<=bazikon.m_t.distance):
                    break
                else:
                    print("lotfan etelaat ra sahih vared konid!!!!")
                    pass
    return mohreh,direction,distance
def search_end(zamin_b):
    lower=0
    upper=0
    for i in range(len(zamin_b)):
        for j in range(len(zamin_b[0])):
            if(zamin_b[i][j]!=None):
                if(zamin_b[i][j].name.isupper()==True):
                    upper+=1
                else:
                    lower+=1
    if(upper!=0 and lower==0):
        return 1
    elif(upper==0 and lower!=0):
        return 0
    else:
        return -1
def run_bazi(bazikon_1,bazikon_2,zamin_b):
    if(random.randrange(10)%2!=0):
        tu_karbar=interface_b(bazikon_1)
        mohreh=None
        if(tu_karbar[0]=="e"):
            mohreh=bazikon_1.m_e
        elif(tu_karbar[0]=="h"):
            mohreh=bazikon_1.m_h
        else:
            mohreh=bazikon_1.m_t
        bazikon_1.move(mohreh,tu_karbar[1],tu_karbar[2],zamin_b)
        print_z(zamin_b)
    else:
        tu_karbar=interface_b(bazikon_2)
        mohreh=None
        if(tu_karbar[0]=="E"):
            mohreh=bazikon_2.m_e
        elif(tu_karbar[0]=="H"):
            mohreh=bazikon_2.m_h
        else:
            mohreh=bazikon_2.m_t
        bazikon_2.move(mohreh,tu_karbar[1],tu_karbar[2],zamin_b)
        print_z(zamin_b)
print_z(zamin_b)
while 1:
    run_bazi(bazikon_1,bazikon_2,zamin_b)
    if(search_end(zamin_b)==1):
        print("bazikon 2 winnnnnnnner!")
        break
    elif(search_end(zamin_b)==0):
        print("bazikon1 winnnnnnnnner!")
        break
    else:
        pass