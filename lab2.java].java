set ns [new simulator]
set nf [open PA1.nam w]
$ns namtrace-all$nf
set tf [open PA1.tr w]
$ns trace-all $tf

proc finish { } {
global ns nf tf
$ns flush-trace
close $nf
closed $tf
exec nam PA1.nam &
exit 0
}

set n0 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

$ns duplex-link $n0 $n2 200 Mb 100ms DropTail
$ns duplex-link $n2 $n3 1 Mb  1000ms DropTail 
$ns duplex-link $n0 $n2 10

$udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
$cbr0 [new Applicationn/Traffic/CBR]
$cbr0 set packetSize_500
cbr0 set interval_0.001
$cbr attach-agent $udp0
$cbr0 null0 [new Agent/Null]
$ns attach-agent $null0
$ns connect $udp0 $null0

$ns at 1.0 "cbr0 start" 
$ns at 1.0 "finish"
$ns run



BEGIN{ c=0;} 
{ 
 if($1=="d") 
 { c++; 
printf("%s\t%s\n",$5,$11); 
 } 
} 
END
{ 
printf("The number of packets dropped =%d\n",c); 
} 