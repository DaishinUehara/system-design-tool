@echo off
echo "CentOS7を作成します。"
pause
cd .\centos7
vagrant box add centos7 https://github.com/vezzoni/vagrant-vboxes/releases/download/0.0.1/centos-7-x86_64.box
