#!/usr/bin/perl

use strict;
use warnings;

use FindBin;
use File::Path qw(make_path);



# +---------------------------
# | Hardcoded information
# +---------------------------
my $cwd = "$FindBin::Bin";
my $templatePomFile = "$cwd/pom_template.xml";



# +---------------------------
# | Global information
# +---------------------------
my ($gid, $aid, $ver);
my $projectName;



# +---------------------------
# | Main
# +---------------------------

### Arguments check.
if (scalar @ARGV == 4) {
    $projectName = shift @ARGV;
    $gid         = shift @ARGV;
    $aid         = shift @ARGV;
    $ver         = shift @ARGV;
}
else {

    print "Enter the project's name :  ";
    chomp($projectName = <STDIN>);
    
    print "Enter the group ID       :  ";
    chomp($gid = <STDIN>);
    
    print "Enter the artifact ID    :  ";
    chomp($aid = <STDIN>);
    
    print "Enter the version        :  ";
    chomp($ver = <STDIN>);
}



### Convert the gid from dots into slashes.
my $gidPath = $gid;
$gidPath =~ s|\.|\/|g;



### Create the project's folder structure.
print "Generate tree structure.\n";
make_path ("${projectName}/src/main/java/${gidPath}/${aid}", {chmod => 0775});
make_path ("${projectName}/src/test/java/${gidPath}/${aid}", {chmod => 0775});
print "ok.\n\n";



### Generate the pom file from the template.
print "Generate the pom file from the template.\n";
open (T, $templatePomFile) or die;
my @lines = <T>;
close (T);

open (N, ">$projectName/pom.xml") or die;
foreach my $line (@lines) {
    $line =~ s|__GID__|$gid|;
    $line =~ s|__AID__|$aid|;
    $line =~ s|__VER__|$ver|;

    print N $line;
}
close (N);

print "ok.\n\n";

